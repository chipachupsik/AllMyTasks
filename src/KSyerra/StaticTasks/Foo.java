package KSyerra.StaticTasks;

public class Foo {
    Integer i = 3;
    int j = 2;

    public void go() {
        j = i;
        System.out.println(j);
        System.out.println(i);
    }
}

class FooTestDrive {
    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.go();
    }
}
