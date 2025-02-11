package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.model.Fruit;

import java.util.List;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    void deleteFruit(int id);
    Fruit updateFruit(Fruit fruit);
    Fruit getFruit(int id);
    List<Fruit> getFruits();
}