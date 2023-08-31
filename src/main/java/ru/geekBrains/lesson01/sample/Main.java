package ru.geekBrains.lesson01.sample;

import ru.geekBrains.lesson01.regular.OtherClass;
import ru.geekBrains.lesson01.regular.Decorator;

public class Main {
    public static void main(String[] args) {

        int result = OtherClass.add(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.sub(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.mul(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.div(2, 2);
        System.out.println(Decorator.decorate(result));
    }
}
