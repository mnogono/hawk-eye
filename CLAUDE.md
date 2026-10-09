# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Что это за репозиторий

HAWK-EYE Phoenix (слоган «Reborn to fly higher») — переписывание десктопного ПО HAWK-EYE (управление геохимическим прибором HAWK пиролиза/окисления, аналог Rock-Eval) в веб-приложение. Кода новой версии пока нет. Ранний прототип примерно десятилетней давности удалён из `master`, он остался только в истории git (см. `docs/spec.md` §12).

Документы — в `docs/`:
- `docs/roadmap.md` — для заказчика, на английском: обоснование, стек, модули, дорожная карта по версиям;
- `docs/spec.md` — техническая спецификация для разработки, на русском: архитектура, стек и все технические решения со статусами «Решено / Предложение / Открыто», открытые вопросы.

Общение с пользователем — на русском.

Стек кратко: Java 25 LTS, Spring Boot с embedded Tomcat, Thymeleaf + htmx, SSE, Bootstrap 5.3 + Tom Select + Bootstrap Icons (SVG-спрайт), Chart.js, Tabulator, TypeScript + Vite, PostgreSQL 18 + Liquibase, Maven, Inno Setup. Целевая ОС — Windows; Linux не делаем, но технологии выбираем переносимые. **Без Vaadin.** Подробности и обоснования — только в `docs/spec.md`; новые технические решения записывать туда, а не сюда.

Исходная первая версия: `C:wdbhawk` (git; раньше SVN). Это эталон функциональности — её только читать, не менять.

## Документы дорожной карты и спецификации

- **Источник истины — `docs/roadmap.md` и `docs/spec.md`.** Все правки вносить только в них.
- `docs/roadmap.docx` и `docs/spec.docx` — производные, хранятся в git (коммитятся вместе с md, из которых собраны), руками не править. Собирать **только по явной просьбе пользователя** (после правок md не пересобирать автоматически); по команде «собрать docx» собираются **оба** — `docs/build-docx.cmd` (pandoc 3.6; скрипт ищет `C:\Program Files\Pandoc\pandoc.exe`, затем `%LOCALAPPDATA%\Pandoc\pandoc.exe`, затем PATH). DOCX — формат для загрузки в Google Docs (импортируется точнее, чем ODT); ODT не генерируем.
- Аналогия: маршрут Екатеринбург → Хьюстон, каждый город — выпуск ПО (0.1 Yekaterinburg … 1.0 Houston). У каждой версии: аналогия курсивом, Goal, Along the way, What's in this release, Not included yet. Заказчику показываем только общий срок версии, без сроков по пунктам; сроки считаются на одного разработчика, в каждую версию заложено 0,5 нед. на выпуск и правки по отзывам; каждая версия должна давать заказчику функции, которые он может попробовать и прокомментировать.
- Автотесты обязательны в каждой версии и входят в её срок (отдельный пункт «Automated tests» в каждой версии, общий раздел «Quality assurance»): алгоритмы (калибровка, расчёт, QC — сверка с результатами старого HAWK-EYE на эталонных данных), CRUD, сериализация/десериализация, миграция, создание бэкапа и восстановление из него, связь с прибором на эмуляторе Sparx — всё основное, что можно проверить кодом.
- Карта маршрута `docs/images/route-map.png` генерируется скриптом `docs/tools/route-map` (d3-geo + world-atlas, данные Natural Earth — общественное достояние; PNG рендерит ImageMagick): `cd docs/tools/route-map && npm install && npm run build`. Подписи городов и их смещения — массив `STOPS` в `build-map.mjs`. Скриншоты Google Maps не использовать (лицензия).
- Python в системе — заглушка WindowsApps (не работает); есть `node`, pandoc, ImageMagick.

## Архитектура исходного HAWK-EYE (`C:\w\vdb\hawk`)

**Процессы.** `client/vdbMain.exe` (C++ хост UI + JS-движок ES3 с COM-плагинами `client/plugins/*.plg`) → `server/vdbServ/vdbServ.exe` (проприетарный SIAMS VDB-прокси на `127.0.0.1:7111`, `client/bin/vdb.ini`) → MSSQL `hawk_v1_0` через ODBC DSN `server/vdbServ/hawk/hawk.dsn`. `vdbMainService.exe` — то же JS-приложение под пользователем `windows-service` без окна (`client/run_service.cmd`); оно ведёт съём данных с прибора. Ветвление по имени пользователя — `client/bin/scripts/enter.js`, `stimer.js`.

**UI ↔ сервис общаются только через БД**, сокетов нет:
- команды — таблица `cmd_queue` (`cmd_queue_name/_arguments/_dest/_respond`): отправитель вставляет строку, получатель опрашивает `dest=X AND respond IS NULL` и пишет JSON-ответ (`objects_ex/modules/orm/cmd_queue.js`, `objects_ex/modules/cmd_queue/*`, UI — `client/bin/dashboardEx.js`);
- онлайн-данные — `settings.online_signals_raw` и таблица `curve` через «online sample» (`objects_ex/modules/hawk_instrument_service.js`).
Новая версия по плану убирает это разделение.

**Конвенции клиента** (`client/bin`): экран = `name.pgf` (бинарная UTF-16 раскладка формы) + `name.js` (обработчики `onFormLoadData`, `onExtTbl*_<ctrl>`); `.pgc` — бинарное описание колонок грида для типа записи; `.par/.ini/.xml/.json` — текстовые конфиги (`oc_serv/oc_serv.par` связывает create/edit-мастера записей со скриптами `oc_*.js`). Загрузчик модулей — `bin/objects/modules.js` (`modules.get("a.b")`); платформенные модули — `objects/modules/*`, специфика HAWK — `objects_ex/modules/*`; ORM ActiveRecord — `objects/modules/orm/active_record.js` + `objects_ex/modules/orm/*`. Библиотека `lib.*` — `client/libEx/lib*.js`.

**Прибор.** Две генерации плат, настройка `pcb_manufacture` (`default_system_settings.json`):
- **Sparx** (актуальная) — JSON-RPC 2.0 поверх HTTP POST: `objects_ex/modules/pcb/sparx/sparx_api.js` (`exec_command`, `exec_watlow_command`, `exec_pedestal_command`, `exec_autoloader_command`, `exec_acq_command`, `get_acq_data`, `get_sensors_status`, `heartbeat`…); драйверы узлов — `objects_ex/modules/pcb/` (oven, watlow, pedestal, thermocouple, flow_control, epc_display, auto_loader). Автозагрузчик EzAxis: ASCII-команды (`documents/Command_Set_EZHR17EN.pdf`) в hex туннелируются через `exec_autoloader_command`.
- **Wildcat** (старая) — TCP к NetBurner + автозагрузчик по COM-порту 9600 8N1.
- Эмуляторы: `instrument/sparx/server.py` (JSON-RPC на `127.0.0.1:10000`, состояние в `hawk_instrument.py`; работает на **Python 3** — упоминания 2.7 в README и `server.cmd` устарели, см. `spec.md` §7; в новой версии используется как есть для автотестов), `instrument/sparx/epc_display.py` (порт 20000); `VirtualAutoLoaderEmulator.cmd` + VSPE — для Wildcat.
- Протокол Sparx: `documents/Comm Protocol*.pdf`, `documents/HAWK API.docx`.

**Предметная область.** project → well → wellbore → sequence → sample → curve (`curve_data` blob). Методы (Pyrolysis, S3, TOC, TOC_CC, Bulk Rock, Kinetics, POPI, HAWK PAM) — `objects/method/methodEx/MethodScript*.js`, `objects_ex/modules/method_script/*`; программа температур = фазы (pyrolysis/oxidation) → шаги → зоны. Калибровки — `objects/calibrations`, QC — `objects/qc` (варианты по форматам данных `bin_hwk`, `csv_rox_*`). Расчёты — «calculation spreadsheet»: ячейки содержат `=JS-выражения` с функциями BaseLine/Smooth/LocalMax (`spreadsheet/spreadsheet_hawk.js`, `default_calibration_calculation_spreadsheet.json`) — логика расчёта хранится в пользовательских данных как код, это главный риск переноса. Отчёты — шаблоны OpenOffice `.odt` + submodule `client/bin/reports/open_office_ext` (sky-report), Excel, wkhtmltopdf.

**БД** (`server/data/create_sql_db.sql`, база поднимается из `hawk_v1_0.bak`, затем идемпотентные `add_col`). Все таблицы имеют служебные `vdb_id/vdb_rec/vdb_cdate/vdb_edate/vdb_sid`. Группы: ядро (sample, sample_param, sequence, method, calibration*, calculation_spreadsheet, curve, curve_style, settings), иерархия (project, well, wellbore), `cm_*`, прибор (cmd_queue, instrument, instrument_log, hawk_event, maintenance), системные EAV `vdb_key/vdb_rel/vdb_str/vdb_int/vdb_flt/vdb_mem/vdb_sys`.

**Тесты исходника.** `client/run tests.cmd` → `bin/scripts/pre-commit.bat` запускает `vdbMain.exe` под `autoTest`, тот вызывает `lib.tests.runUnitTests()`; результат — маркер-файл `unitTestsPassed`/`unitTestsError`. Фреймворк свой (`libEx/libTests.js`): функции `test*` в `objects/tests` и `objects_ex/tests` возвращают строку ошибки при провале. Отдельный тест — через форму `libEx/unitTests.pgf`.

**Сборка исходника.** `build/build.cmd` (экспорт из SVN, версия = ревизия SVN, Inno Setup 5.5.6 по шаблонам `build/Projects/HAWK/*_template.iss`); полный установщик — `distr/`.

**Документация исходника:** `C:\w\vdb\hawk\documents` — user guide, system administrator guide, quality control, расчёты HAWK/RE-6 (`HAWK and RE-6 calculations.xls`), протоколы обмена.
