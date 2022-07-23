package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Pattern;

public enum Intent {

    // メッセージの正規表現パターンに対応するやりとり状態の定義
    TRANSLATE("^(.*)を翻訳"),
    UNKNOWN(".+");

    private final String regexp;

    private Intent(String regexp) {
        this.regexp = regexp;
    }

    /**
     * メッセージからやりとり状態を判断するメソッド
     * @param text
     * @return intent
     * @return UNKNOWN
     */
    public static Intent whichIntent(String text) {
        // 全てのIntent(UNKNOWN)を取得
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        // 引数 text が　UNKNOWN のパターンに当てはまるかチェック
        // 当てはまった方を戻り値とする
        for (Intent intent : set) {
            if (Pattern.matches(intent.regexp, text)) {
                return intent;
            }
        }
        return UNKNOWN;
    }

    public String getRegexp() {
        return regexp;
    }
}
