@echo off
rem roadmap.md and spec.md are the sources; roadmap.docx and spec.docx are generated from them
rem (DOCX is the format to upload to Google Docs).
cd /d "%~dp0"
"C:\Program Files\Pandoc\pandoc.exe" roadmap.md -o roadmap.docx || exit /b 1
"C:\Program Files\Pandoc\pandoc.exe" spec.md -o spec.docx || exit /b 1
