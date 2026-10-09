@echo off
rem roadmap.md and spec.md are the sources; roadmap.docx and spec.docx are generated from them
rem (DOCX is the format to upload to Google Docs).
cd /d "%~dp0"
set "PANDOC=C:\Program Files\Pandoc\pandoc.exe"
if not exist "%PANDOC%" set "PANDOC=%LOCALAPPDATA%\Pandoc\pandoc.exe"
if not exist "%PANDOC%" set "PANDOC=pandoc"
"%PANDOC%" roadmap.md -o roadmap.docx || exit /b 1
"%PANDOC%" spec.md -o spec.docx || exit /b 1
