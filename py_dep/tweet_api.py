import json, config #標準のjsonモジュールとconfig.pyの読み込み
from requests_oauthlib import OAuth1Session #OAuthのライブラリの読み込み

CK = config.CONSUMER_KEY
CS = config.CONSUMER_SECRET
AT = config.ACCESS_TOKEN
AS = config.ACCESS_TOKEN_SECRET
# OAuth認証で POST method で投稿
twitter = OAuth1Session(CK, CS, AT, AS)

# ツイート投稿用のURL
url = "https://api.twitter.com/1.1/statuses/update.json"

# ツイート本文
params = {"status": "Hello, World!"}
#ツイート
req = twitter.post(url, params = params)

# レスポンスを確認
if req.status_code == 200:
    print ("OK")
else:
    print ("Error: %d" % req.status_code)
