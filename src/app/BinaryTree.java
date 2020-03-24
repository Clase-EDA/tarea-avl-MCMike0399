package app;
import java.util.*;
public class BinaryTree<T extends Comparable<T>> {
    protected NodoBin<T> root;
    public BinaryTree() {
        root = new NodoBin<T>(null); //Centinela
    }
    public BinaryTree(T elem) {
        root = new NodoBin<T>(null); //Centinela
        root.setDer(new NodoBin<T>(elem));
        root.getDer().setPapa(root);
    }
    public ArrayList<T> inOrder() {
        ArrayList<T> lista = new ArrayList<T>();
        inOrder(root.getDer(),lista);
        return lista;
    }
    private void inOrder(NodoBin<T> actual, ArrayList<T> lista) {
        if(actual==null) {
            return;
        }
        inOrder(actual.getIzq(),lista);
        lista.add(actual.getElem());
        inOrder(actual.getDer(),lista);
    }
    public ArrayList<T> preOrder() {
        ArrayList<T> lista = new ArrayList<T>();
        preOrder(root.getDer(),lista);
        return lista;
    }
    private void preOrder(NodoBin<T> actual, ArrayList<T> lista) {
        if(actual==null) {
            return;
        }
        lista.add(actual.getElem());
        preOrder(actual.getIzq(),lista);
        preOrder(actual.getDer(),lista);
    }
    public ArrayList<T> postOrder() {
        ArrayList<T> lista = new ArrayList<T>();
        postOrder(root.getDer(),lista);
        return lista;
    }
    private void postOrder(NodoBin<T> actual, ArrayList<T> lista) {
        if(actual==null) {
            return;
        }
        postOrder(actual.getIzq(),lista);
        postOrder(actual.getDer(),lista);
        lista.add(actual.getElem());
    }
    public ArrayList<T> levelOrder() {
        ArrayList<T> lista = new ArrayList<T>();
        Queue<NodoBin<T>> q = new LinkedList<NodoBin<T>>();
        NodoBin<T> actual = null;
        q.add(root.getDer());
        while(!q.isEmpty()) {
            actual = q.remove(); 
            if(actual.getIzq()!=null) {
                q.add(actual.getIzq());
            }
            if(actual.getDer()!=null) {
                q.add(actual.getDer());
            }
            lista.add(actual.getElem());
        }
        return lista;
    }
    public ArrayList<T> leverlOrder() {
        ArrayList<T> lista = new ArrayList<T>();
        int h = getAltura();
        for(int i=0; i<=h; i++) {
            lista = printLevelOrder(root.getDer(), i, lista);
        }  
        return lista;
    }
    public ArrayList<T> printLevelOrder(NodoBin<T> actual, int level, ArrayList<T> lista) {
        if(actual==null) {
            return lista;
        }
        if(level==1) {
            lista.add(actual.getElem());
            return lista;
        }
        else if(level>1) {
            printLevelOrder(actual.getIzq(), --level, lista);
            printLevelOrder(actual.getDer(), --level, lista);
        }
        return lista;
    }
    public int getAltura() {
        return getAltura(root.getDer());
    }
    protected int getAltura(NodoBin<T> actual) {
        if(actual==null) {
            return 0;
        }
        int aIzq = getAltura(actual.getIzq())+1;
        int aDer = getAltura(actual.getDer())+1;
        if(aIzq<aDer) {
            return aDer;
        }
        else {
            return aIzq;
        }
    }
}