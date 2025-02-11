package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.exception.FruitAlreadyExistsException;
import cat.itacademy.s04.t02.n01.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.FruitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public Fruit addFruit(Fruit fruit) {
        if (fruitRepository.findByName(fruit.getName()).isPresent()) {
            throw new FruitAlreadyExistsException("Fruit with name " + fruit.getName() + " already exists");
        }
        return fruitRepository.save(fruit);
    }

    @Override
    public void deleteFruit(int id) {
        if (!fruitRepository.existsById(id)) {
            throw new FruitNotFoundException(id);
        }
        fruitRepository.deleteById(id);
    }

    @Override
    public Fruit updateFruit(Fruit fruit) {
        Fruit existingFruit = fruitRepository.findById(fruit.getId())
                .orElseThrow(() -> new FruitNotFoundException(fruit.getId()));

        fruitRepository.findByName(fruit.getName()).ifPresent(existing -> {
            if (existing.getId() != fruit.getId()) {
                throw new FruitAlreadyExistsException("Fruit with name " + fruit.getName() + " already exists");
            }
        });

        existingFruit.setName(fruit.getName());
        existingFruit.setWeight(fruit.getWeight());

        return fruitRepository.save(existingFruit);
    }

    @Override
    public Fruit getFruit(int id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));
    }

    @Override
    public List<Fruit> getFruits() {
        return fruitRepository.findAll();
    }
}
