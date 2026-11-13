#!/bin/bash
# usage: render.sh name W H   (renders name.html -> name.png)
C="/c/Program Files/Google/Chrome/Application/chrome.exe"
UD="$(cygpath -w /c/Users/91740/AppData/Local/Temp/claude/1d0168da96fb2023/0e833f0a-338f-4c7a-896c-44c34f7dbda2/scratchpad/chrome-prof)"
timeout 60 "$C" --headless=new --user-data-dir="$UD" --no-first-run --disable-gpu --hide-scrollbars --default-background-color=00000000 --force-device-scale-factor=1 --window-size=$2,$3 --screenshot="$(cygpath -w $PWD/$1.png)" "file:///$(cygpath -m $PWD/$1.html)" >/dev/null 2>&1
