package com.dennis.bytecode;

/**
 * 描述：静态分派（重载方法的调用，变量的声明，编译期即可确定）
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/30 21:07
 */
public class BycodeTest04 {
    public void getAnimalName(Animal animal) {
        System.out.println("please choose a specific animal ");
    }

    public void getAnimalName(Cat cat) {
        System.out.println("name:" + cat.name);
    }

    public void getAnimalName(Dog dog) {
        System.out.println("name:" + dog.name);
    }

    public void getAnimalName(Tiger tiger) {
        System.out.println("name:" + tiger.name);
    }

    public static void main(String[] args) {

        //【1】我们把“Animal”称为变量的静态类型，后面的“Cat”称为变量的实际类型，静态类型和实际类型在程序中都可以发生一些变化，
        // 区别是静态类型的变化仅仅在编码使用时发生，变量本身的静态类型不会被改变，并且最终的静态类型在编译器可知；而实际类型变化的
        // 结果在运行期才确定，编译器在编译期并不知道一个对象的实际类型是什么。
        //【2】变量的声明为一种静态分派，编译期就已确定，变量cat dog tiger均为Animal静态类型而非实际运行时期的实际类型
        Animal cat = new Cat();
        Animal dog = new Dog();
        Animal tiger = new Tiger();

        // 【3】方法的重载，编译器在重载时是通过参数的静态类型而不是实际类型作为判定的依据，本质上方法的重载就是更改参数列表声
        // 明时的类型或则个数，这些因素均在编译期即可确定，因此方法的重载也是静态分派行为；
        // 所有以下调用均调用的是同一个版本的方法：getAnimalName(Animal animal)
        BycodeTest04 bycodeTest04 = new BycodeTest04();
        bycodeTest04.getAnimalName(cat);
        bycodeTest04.getAnimalName(dog);
        bycodeTest04.getAnimalName(tiger);

        // 类型强转，以更改调用方法版本
        bycodeTest04.getAnimalName((Cat) cat);
    }
}

class Animal {
}

class Cat extends Animal {
    public String name = "cat";
}

class Dog extends Animal {
    public String name = "dog";
}

class Tiger extends Animal {
    public String name = "tiger";
}
