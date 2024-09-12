package com.baeksoo.shop.user;

public class User {
    private String name;
    private int age;

    public void incrementAge() {
        this.age = this.age + 1;
        System.out.println(age);
    }
    public void setAge(Integer age) {
        if (age > 0 && age < 100) {
            this.age = age;
        } else {
            System.out.println("나이는 1살 부터 100살 사이로만 입력 가능");
        }
    }

}
