package com.vrv.vap.core.common;

public class ValueText {

    private String context;
    private Object value;

    public ValueText() {
    }

    /**
     * context ： 文本显示内容
     * value： 对应的属性值
     * 例如： 内容文本1 - --->  1
     *
     * @param context
     * @param value
     */
    public ValueText(String context, Object value) {
        this.value = value;
        this.context = context;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
