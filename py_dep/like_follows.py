#tweepyライブラリ、コンスタンスをインポート
import tweepy, config
import traceback

#カスタマーキー
CK = config.CONSUMER_KEY
CS = config.CONSUMER_SECRET
#アクセストークン
AT = config.ACCESS_TOKEN
AS = config.ACCESS_TOKEN_SECRET

auth = tweepy.OAuthHandler(CK,CS)
auth.set_access_token(AT,AS)
#インスタンス生成
api = tweepy.API(auth)

q = "#IZ*ONE" #ツイート検索キー
count = 500 #取得数

search_result = api.search(q=q,count=count)

for result in search_result:
    #ユーザID取得
    username = result.user._json['screen_name']
    print("")
    print("")
    print("表示ユーザID：@{0}".format(username))
    #ツイートからユーザID取得
    user_id = result.id
    print("ツイートID：{0}".format(user_id))
    user = result.user.name
    print("ユーザ名：{0}".format(user))
    tweet = result.text
    print("***************************ツイート内容*******************************")
    print(tweet)
    print("********************************************************************")
    time = result.created_at
    print(time)
    try:
        api.create_favorite(user_id)
        print("いいねしました")
        api.create_friendship(username)
        print("フォローしました")
    except:
        traceback.print_exc()
        print("既にフォロー中")
