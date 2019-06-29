import tweepy, config, traceback

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

my_userid = ""

followers_id = api.followers_ids(my_userid)
following_id = api.friends_ids(my_userid)

for following in following_id:
    if following not in followers_id:
        print(following)
        try:
            api.destroy_friendship(following)
        except:
            print("リムーブすることができません。（ユーザID：{0}）".format(following))
            traceback.print_exc()
