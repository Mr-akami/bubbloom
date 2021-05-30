# 開発環境
# React + Typescript

# https://hub.docker.com/_/node
FROM node:16-slim

ENV LANG=C.UTF-8
ENV TZ=Asia/Tokyo


# ワーキングを以下の通り作成
ENV bubbloom_frontend_dir /usr/bubbloom/frontend
RUN mkdir -p ${bubbloom_frontend_dir}

WORKDIR ${bubbloom_frontend_dir}

# Firebase SDKインストール
RUN npm install --save firebase

# reactに必要な依存パッケージをインストール
# create-react-appで最新の依存パッケージが入る。作成されたテンプレートは不要なので削除。
RUN npx create-react-app my-app --template typescript
RUN rm -rf my-app

