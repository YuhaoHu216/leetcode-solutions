@echo off
echo 正在推送到 Gitee...
git push gitee master

echo.
echo 正在推送到 GitHub...
git push github master

echo.
echo 同步完成！
pause
