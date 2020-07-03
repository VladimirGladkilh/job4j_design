package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                return false;
            }
            data.addAll(el.children);
        }
        return true;
    }


    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;

        Optional<Node<E>> findParent = findBy(parent);
        if (findParent != null) {
            Node<E> newNode = new Node<>(child);

            Node<E> node = findParent.get();
            List<Node<E>> nodeList = node.children;
            if (nodeList.indexOf(newNode) < 0) {
                nodeList.add(newNode);
                rsl = true;
            }
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
