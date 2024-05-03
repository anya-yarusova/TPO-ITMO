package com.annyarusova.task2;

public class AvlTree<T extends Comparable<? super T>> {
    public class AvlNode {
        public T element;

        public AvlNode left;

        public AvlNode right;

        private int height;

        public AvlNode(T el) {
            this(el, null, null);
        }

        public AvlNode(T el, AvlNode l, AvlNode r) {
            element = el;
            left = l;
            right = r;
        }

        public int getLeftHeight() {
            return left == null ? 0 : left.height;
        }

        public int getRightHeight() {
            return right == null ? 0 : right.height;
        }
    }

    public AvlNode root;

    public void insert(T x) {
        root = insert(x, root);
    }

    private AvlNode insert(T x, AvlNode t) {
        if (t == null)
            t = new AvlNode(x);
        else if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left);

            if (t.getLeftHeight() - t.getRightHeight() == 2)
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
        }
        else {
            t.right = insert(x, t.right);

            if (t.getRightHeight() - t.getLeftHeight() == 2)
                if (x.compareTo(t.right.element) > 0)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        }

        t.height = Math.max(t.getLeftHeight(), t.getRightHeight()) + 1;
        return t;
    }

    private AvlNode rotateWithLeftChild(AvlNode k2) {
        AvlNode k1 = k2.left;

        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(k2.getLeftHeight(), k2.getRightHeight()) + 1;
        k1.height = Math.max(k1.getLeftHeight(), k2.height) + 1;

        return k1;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode rotateWithRightChild(AvlNode k1) {
        AvlNode k2 = k1.right;

        k1.right = k2.left;
        k2.left = k1;

        k1.height = Math.max(k1.getLeftHeight(), k1.getRightHeight()) + 1;
        k2.height = Math.max(k2.getRightHeight(), k1.height) + 1;

        return k2;
    }

    private AvlNode doubleWithRightChild(AvlNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public String print() {
        StringBuilder str = new StringBuilder();
        print(root, str, " ");
        return str.toString().trim();
    }

    private void print(AvlNode t, StringBuilder sb, String separator) {
        if (t != null) {
            print(t.left, sb, separator);
            sb.append(t.element.toString());
            sb.append(separator);
            print(t.right, sb, separator);
        }
    }

    public void delete(T x) {
        root = delete(x, root);
    }

    private AvlNode delete(T x, AvlNode t) {
        if (t == null) {
            return null;
        }

        if (x.compareTo(t.element) < 0) {
            t.left = delete(x, t.left);
            int l = t.left != null ? t.getLeftHeight() : 0;

            if ((t.right != null) && (t.getRightHeight() - l >= 2)) {
                t = getAvlNode(t);
            }
        }
        else if (x.compareTo(t.element) > 0) {
            t.right = delete(x, t.right);
            int r = t.getRightHeight();

            if ((t.left != null) && (t.getLeftHeight() - r >= 2)) {
                int leftHeight = t.left.getLeftHeight();
                int rightHeight = t.left.getRightHeight();

                if (leftHeight >= rightHeight)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithLeftChild(t);
            }
        }
        else if (t.left != null) {
            t.element = findMax(t.left).element;
            t.left = delete(t.element, t.left);

            if ((t.right != null) && (t.getRightHeight() - t.getLeftHeight() >= 2)) {
                t = getAvlNode(t);
            }
        }
        else
            t = t.right;

        if (t != null) {
            t.height = Math.max(t.getLeftHeight(), t.getRightHeight()) + 1;
        }
        return t;
    }

    private AvlNode findMax(AvlNode t) {
        if (t == null)
            return t;

        while (t.right != null)
            t = t.right;
        return t;
    }

    private AvlNode getAvlNode(AvlNode t) {
        int rightHeight = t.right.getRightHeight();
        int leftHeight = t.right.getLeftHeight();

        if (rightHeight >= leftHeight)
            t = rotateWithRightChild(t);
        else
            t = doubleWithRightChild(t);
        return t;
    }

    public boolean find(T x) {
        return find(x, root);
    }

    private boolean find(T x, AvlNode t) {
        if (t == null) {
            return false;
        } else if (x.compareTo(t.element) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            return find(x, t.right);
        }

        return true;
    }
}
