from flask import Flask, request

from googletrans import Translator
import re

app = Flask(__name__)
app.config["JSON_AS_ASCII"] = False


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/translate', methods=["GET"])
def document_translate():

    # クエリのパラメータの取得
    req = request.args
    document = req.get("doc")
    translator = Translator()

    # 英数字の場合の正規表現パターン定義
    regexp = re.compile(r"[\da-zA-Z]+")

    if re.match(regexp, document) is not None:
        message = translator.translate(document, src='en', dest='ja')
        return message.text
    else:
        message = translator.translate(document, src='ja', dest='en')
        return message.text


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
