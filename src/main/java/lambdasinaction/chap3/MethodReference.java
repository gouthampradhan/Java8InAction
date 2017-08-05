package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by gouthamvidyapradhan on 05/08/2017.
 */
public class MethodReference {

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Supplier<Apple> s = Apple::new;
        Apple a = s.get();
        System.out.println("Apple created ->" + a);

        Function<Integer, Apple> f = Apple::new;
        List<Apple> applesWithWeight = map(Arrays.asList(12, 11, 4, 5, 7, 10), f);
        System.out.println("Apples with weight created");
        System.out.println(applesWithWeight);

        List<PropertyMap<Integer, String>> list = Arrays.asList(new PropertyMap<>(12, "green"), new PropertyMap<>(14, "red"),
                new PropertyMap<>(12, "blue"), new PropertyMap<>(12, "pink"));
        BiFunction<Integer, String, Apple> bf = Apple::new;
        List<Apple> applesWithWeightAndColor = map(list, bf);
        System.out.println("Apples with weight and color created");
        System.out.println(applesWithWeightAndColor);
    }

    private static <I, A> List<A> map(List<I> weight, Function<I, A> f){
        List<A> result = new ArrayList<>();
        for(I w : weight){
            result.add(f.apply(w));
        }
        return result;
    }

    private static <W, C, A> List<A> map(List<PropertyMap<W, C>> list, BiFunction<W, C, A> f){
        List<A> result = new ArrayList<>();
        for(PropertyMap<W, C> p : list){
            result.add(f.apply(p.getA(), p.getB()));
        }
        return result;
    }

    private static class PropertyMap<A, B>{
        private A a;
        private B b;

        PropertyMap(A a, B b){
            this.a = a;
            this.b = b;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple() {}

        public Apple(int weight){
            this.weight = weight;
        }

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
