package app;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    protected int cont;
    public BinarySearchTree() {
        super();
        cont=0;
    }
    public BinarySearchTree(T elem) {
        super(elem);
        cont=1;
    }
    public NodoBin<T> busca(T elem) {
        NodoBin<T> actual = root.getDer();
        while(actual!=null) {
            if(actual.getElem().equals(elem)) {
                return actual;
            }
            if(elem.compareTo(actual.getElem())<0) {
                actual = actual.getIzq();
            }
            else {
                actual = actual.getDer();
            }
        }
        return null; //nunca lo encontró
    }
    public void inserta(T elem) {
        NodoBin<T> actual = root;
        if(root.getDer()==null) {
            root.setDer(new NodoBin<T>(elem));
            root.getDer().setPapa(root);
            return;
        }
        actual = actual.getDer();
        NodoBin<T> papa = actual.getDer();
        while(actual!=null) {
            papa = actual;
            if(elem.compareTo(actual.getElem())<0) {
                actual = actual.getIzq();
            }
            else {
                actual = actual.getDer();
            }
        }
        papa.cuelga(new NodoBin<T>(elem));
        cont++;
    }
    public void delete(T elem) {
        NodoBin<T> actual = busca(elem);
        if(actual==null) {
            return;
        }
        if(cont==1) {
            root.setDer(null);
            cont=0;
            return;
        }
        if(actual.getIzq()==null && actual.getDer()==null) { //Si es una hoja
            if(actual.getPapa().getIzq().equals(actual)) {
                actual.getPapa().setIzq(null);
            }
            else {
                actual.getPapa().setDer(null);
            }
        }
        else { //no es una hoja
            //Cuando sólo es un hijo
            if(actual.getIzq()==null || actual.getDer()==null) {
                if(actual.getIzq()!=null && actual.getDer()==null) {
                    actual.getPapa().cuelga(actual.getIzq());
                }
                else if(actual.getIzq()==null && actual.getDer()!=null) {
                    actual.getPapa().cuelga(actual.getDer());
                }
            }
            else {
                NodoBin<T> actual2 = actual;
                actual2 = actual2.getDer();
                while(actual2.getIzq()!=null) {
                    actual2 = actual2.getIzq();
                }
                if(actual2.getDer()!=null) {
                    actual2.getPapa().cuelga(actual2.getDer());
                }
                else {
                    actual2.getPapa().setIzq(null);
                }
                actual.setElem(actual2.getElem());
            }
        }
        cont--;
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> arbol = new BinarySearchTree<Integer>();
        arbol.inserta(5);
        arbol.inserta(3);
        arbol.inserta(7);
        arbol.inserta(2);
        arbol.inserta(4);
        arbol.inserta(6);
        arbol.inserta(8);
        System.out.println(arbol.preOrder().toString());
        System.out.println(arbol.root.getDer().getDer().getIzq().getPapa().getDer().getElem());
        arbol.delete(5);
        System.out.println(arbol.preOrder().toString());
        System.out.println(arbol.inOrder().toString());
    }
}