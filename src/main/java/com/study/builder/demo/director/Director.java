package com.study.builder.demo.director;

import com.study.builder.demo.actor.Actor;
import com.study.builder.demo.dresser.AbstractDresser;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/9:59
 * @Desc: 导演--指挥者
 **/
public class Director {

    public Actor construct(AbstractDresser dresser){
        Actor actor = new Actor();

        actor.getSculpt().add(dresser.head());
        actor.getSculpt().add(dresser.face());
        actor.getSculpt().add(dresser.jacket());
        actor.getSculpt().add(dresser.trousers());

        return actor;
    }

}
