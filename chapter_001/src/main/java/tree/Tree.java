package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;
    private boolean isBinTree = true;
    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return isBinTree;
    }


    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;

        Optional<Node<E>> findParent = findBy(parent);
        Optional<Node<E>> findChild = findBy(child);
        if (findParent.isPresent() && !findChild.isPresent()) {
            findParent.get().children.add(new Node<>(child));
            if (findParent.get().children.size() > 2){
                this.isBinTree = false;
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
