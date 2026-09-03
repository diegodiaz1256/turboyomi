#!/bin/bash
set -e

rsync -a --checksum --delete --exclude .git --exclude .gitignore --exclude repo.json ../main/repo/ .
git config --global user.email "github-actions[bot]@users.noreply.github.com"
git config --global user.name "github-actions[bot]"
git status
if [ -n "$(git status --porcelain)" ]; then
    git add .
    git commit -m "Update extensions repo"
    git push

    # Purge cached index on jsDelivr (this fork's own CDN path)
    curl https://purge.jsdelivr.net/gh/diegodiaz1256/turboyomi@repo/index.min.json
else
    echo "No changes to commit"
fi
