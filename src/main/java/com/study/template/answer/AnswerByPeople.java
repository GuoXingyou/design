package com.study.template.answer;

import com.study.template.question.HaThreePart;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/13:52
 * @Desc:
 **/
public class AnswerByPeople extends HaThreePart {
    @Override
    protected String ansFirst() {
        return "B";
    }

    @Override
    protected String ansSecond() {
        return "A";
    }

    @Override
    protected String ansThird() {
        return "A";
    }
}
