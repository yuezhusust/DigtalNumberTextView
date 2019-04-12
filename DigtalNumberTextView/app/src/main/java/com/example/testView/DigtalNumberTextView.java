package com.example.testView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * 具有数字数码显示效果和颜色梯度渐变显示效果的textView,参考了网上的开源项目
 */
public class DigtalNumberTextView extends AppCompatTextView {
    /*textView上方显示的颜色*/
    private String startColor;
    /*textView 下方显示的颜色*/
    private String endColor;
    public DigtalNumberTextView(Context context) {
        super(context, null);
    }

    public DigtalNumberTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DigtalNumberTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.DigtalNumberTextView);
        if (attrArray != null) {
            startColor = attrArray.getString(R.styleable.DigtalNumberTextView_startColor);
            endColor = attrArray.getString(R.styleable.DigtalNumberTextView_endColor);
            attrArray.recycle();
        }
        init();
    }

    /**
     * 调用字体库修改为数字显示的字体
     */
    private void init() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font_digital.ttf");
        this.setTypeface(typeface);
    }
    @Override
    protected void onLayout(boolean changed,
                            int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //如果两个颜色都不为空，则按照上下梯度渐变的方式修改渐变色
        if (!TextUtils.isEmpty(startColor) && !TextUtils.isEmpty(endColor)) {
            if (changed) {
                getPaint().setShader(new LinearGradient(
                        0, getHeight(), 0, 0,
                        StringToColor(startColor), StringToColor(endColor),
                        Shader.TileMode.REPEAT));
            }
        }
    }
    /**
     * #颜色转16进制颜色
     * @param str {String} 颜色
     * @return
     */
    private  int StringToColor(String str) {
        return 0xff000000 | Integer.parseInt(str.substring(2), 16);
    }
}
