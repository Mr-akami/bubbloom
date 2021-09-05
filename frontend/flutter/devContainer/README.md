# flutter_web_develop
Flutterのweb版での環境構築。
こちらのレポジトリをもとに作成
https://github.com/toshi-click/flutter_web_develop.git

## HowTo
1. コンテナのビルド
    ```
    docker-compose build
    ```
1. コンテナを構築、起動
    ```
    docker-compose up -d
    ```
1. 起動したコンテナに入る
    ```
    docker exec -it flutter bash
    ```
1. flutterアプリの場所に移動
    ホストの ../bubbloom-frontend をローカルの usr/bubbloom/frontend にマウントしている
    ```
    cd ${APP_CODE_PATH_CONTAINER}
    ```
2. サーバーを起動
    ```
    flutter run -d web-server --web-port=${WEB_SERVER_PORT} --web-hostname 0.0.0.0
    ```
   下記が表示されたら [http://localhost:8888](http://localhost:8888) にアクセスすればOK
    ```shell
    To hot restart changes while running, press "r" or "R".
    For a more detailed help message, press "h". To quit, press "q".
    ```
3. Flutterをアップグレードする場合
    ```
    flutter upgrade
    ```
4. Webをビルドする場合
    ```
    flutter build web
    ```

## VS Code のRemote Containerでの使い方

1. VS Code をdevContainerで開く
2. 左下の[><]マークをクリックし、”Open Folder in Container”をクリック
3. 自分のいるフォルダ（devContainer）をクリック
   1. 初回の場合、どのファイルでdockerを実行するか選択肢が出るため、docker-compose.ymlを選択

起動後、作業ディレクトリは/usr/local/workspaceのため、コマンドを実行するときは手動でカレントディレクトリを移動させる（Todo 起動時にカレントディレクトリを作業ディレクトリにする）


## Tips

なぜかFlutterのビルド環境が簡単に壊れる。Dockerの多重起動などが原因？  
なぜかわからないけどビルドやサーバ起動ができなくなったら再度Cloneしてください。