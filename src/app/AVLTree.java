package app;
import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    public AVLTree() {
        super();
    }
    public AVLTree(T elem) {
        super(elem);
    }
    public void recorreInverso(NodoBin<T> actual) {
        boolean termine = false;
        NodoBin<T> papa = actual;
        while(!termine) {
            if(actual.getPapa()==root) {
                termine = true;
            }
            else {
                papa = actual.getPapa();
                if(actual==papa.getIzq()) {
                    papa.setFe(papa.getFe()-1);
                }
                else {
                    papa.setFe((papa.getFe()+1));
                }
                /*if(Math.abs(papa.getFe())>1) {
                    papa.getPapa().setDer(rotarDerDer(papa));
                }*/
                if(papa.getFe()==0) {
                    termine=true;
                }
            }
            actual = actual.getPapa();
        }
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
        
        NodoBin<T> nuevo = new NodoBin<T>(elem);
        papa.cuelga(nuevo);
        cont++;
        boolean flag = false;   
        while(!flag) {
            if(nuevo.getPapa()==root) {
                flag = true;
            }
            actualizaFE(nuevo);
            if(Math.abs(nuevo.getFe())>1) {
                NodoBin<T> nodo = determinaRotacion(nuevo);
                nuevo = nodo;
            }
            nuevo = nuevo.getPapa();
        }
    }
    public NodoBin<T> determinaRotacion(NodoBin<T> nodo) {
        if((nodo.getFe()==-2 && nodo.getIzq()!=null) && (nodo.getIzq().getFe()==-1 || nodo.getIzq().getFe()==0)) {
            return rotarIzqIzq(nodo);
        }
        else if((nodo.getFe()==2 && nodo.getDer()!=null) && (nodo.getDer().getFe()==1 || nodo.getDer().getFe()==0)) {
            return rotarDerDer(nodo);
        }
        else if((nodo.getFe()==-2 && nodo.getIzq()!=null) && (nodo.getDer().getFe()==1 || nodo.getDer().getFe()==0)) {
            return rotarIzqDer(nodo);
        }
        else {
            return rotarDerIzq(nodo);
        }
    }
    public NodoBin<T> rotarIzqIzq(NodoBin<T> n) {
        NodoBin<T> alfa,beta,gamma,papa; 
        alfa = n;
        beta = n.getIzq();
        gamma = beta.getIzq();
        papa = n.getPapa();
        alfa.cuelgaH(beta.getDer(),"izq");
        beta.cuelgaH(alfa, "der");
        if(papa!=null){
            if(papa.getIzq()==n)
                papa.cuelgaH(beta,"izq");
            else
                papa.cuelgaH(beta,"der");
        }
        else{
            root.setDer(beta);
            //raiz.papa = null;
        }
        actualizaFE(gamma);
        actualizaFE(alfa);
        actualizaFE(beta);
        papa = beta.getPapa();
        if(papa!=null)
            actualizaFE(papa);
        
        return beta;
    }
    public NodoBin<T> rotarIzqDer(NodoBin<T> n) {
        NodoBin<T> alfa,beta,gamma,papa;
        
        alfa = n;
        beta = n.getIzq();
        gamma = beta.getDer();
        papa = n.getPapa();
        beta.cuelgaH(gamma.getIzq(), "der");
        alfa.cuelgaH(gamma.getDer(), "izq");
        gamma.cuelgaH(beta, "izq");
        gamma.cuelgaH(alfa, "der");
        if(papa!=null){
            if(papa.getIzq()==n) {
                papa.cuelgaH(gamma, "izq");
            }
            else {
                papa.cuelgaH(gamma,"der");
            }
        }
        else{
            root.setDer(gamma);
            //raiz.papa=null;
        }
        actualizaFE(beta);
        actualizaFE(alfa);
        actualizaFE(gamma);
        papa = gamma.getPapa();
        if(papa!=null) {
            actualizaFE(papa);
        }
        return gamma;
    }
    public NodoBin<T> rotarDerDer(NodoBin<T> n) {
        NodoBin<T> alfa,beta,gamma,papa;   
        alfa = n;
        beta = n.getDer();
        gamma = beta.getDer();
        papa = n.getPapa();
        alfa.cuelgaH(beta.getIzq(), "der");
        beta.cuelgaH(alfa, "izq");
        if(papa!=null){
            if(papa.getIzq()==n) {
                papa.cuelgaH(beta, "izq");
            }
            else {
                papa.cuelgaH(beta,"der");
            }
        }
        else{
            root.setDer(beta);
            //root.papa = null;
        }
        actualizaFE(alfa);
        actualizaFE(gamma);
        actualizaFE(beta);
        papa = beta.getPapa();
        if(papa!=null) {
            actualizaFE(papa);
        }
        return beta;
    }
    public NodoBin<T> rotarDerIzq(NodoBin<T> n) {
        NodoBin<T> alfa,beta,gamma,papa;
        
        alfa = n;
        beta = n.getDer();
        gamma = beta.getIzq();
        papa = n.getPapa();
        alfa.cuelgaH(gamma.getIzq(), "der");
        beta.cuelgaH(gamma.getDer(), "izq");
        gamma.cuelgaH(alfa, "izq");
        gamma.cuelgaH(beta, "der");
        if(papa!=null){
            if(papa.getIzq()==n) {
                papa.cuelgaH(gamma, "izq");
            }
            else {
                papa.cuelgaH(gamma,"der");
            }
        }
        else{
            root.setDer(gamma);
            //raiz.papa = null;
        }
        actualizaFE(alfa);
        actualizaFE(beta);
        actualizaFE(gamma);
        papa = gamma.getPapa();
        if(papa!=null) {
            actualizaFE(papa);
        }
        return gamma;
    }
    private NodoBin<T> encuentraElemento(T elem){
        boolean encontrado = false;
        NodoBin<T> actual = root.getDer();
        while(actual!=null && !encontrado){
            if(elem.compareTo(actual.getElem())<0)
                actual = actual.getIzq();
            else if(elem.compareTo(actual.getElem())>0)
                actual = actual.getDer();
            else encontrado = true;
        }
        if(actual==null)
            return null;
        else
            return actual;
    }
    private NodoBin<T> reemplazaSucInorden(NodoBin<T> n){
        NodoBin<T> aux = n.getDer(), temp=n;
        if(n.getDer()==null)
            aux = n.getIzq();
        while(aux.getIzq()!=null){
            temp = aux;
            aux = aux.getIzq();
        }
        n.setElem(aux.getElem());
        return temp;
    }
    public T borra(T elem){
        NodoBin<T> actual = root.getDer(), papa = null;
        boolean band = false;
        actual = encuentraElemento(elem);//Encuentra el nodo que tiene el elemento
        
        papa = actual.getPapa();
        
        if(actual.getIzq()!=null&&actual.getDer()!=null){
            NodoBin<T> temp = reemplazaSucInorden(actual);
            if(temp==actual){
                papa = temp;
                actual = actual.getDer();
            }else{
                papa = temp;
                actual = papa.getIzq();
            }
        }else{
            if(actual.getIzq()!=null&&actual.getDer()==null){
                actual = actual.getIzq();
            }
        }
        if(papa.getIzq()==actual){
            papa.cuelgaH(actual.getDer(),"izq");
        }
        else
            papa.cuelgaH(actual.getDer(),"der");
        band = false;
        while(!band){
            if(actual.getPapa()==root)
                band=true;
            actualizaFE(actual);
            if(Math.abs(actual.getFe())>1){
                NodoBin<T> n = determinaRotacion(actual);
                actual = n;
            }
            actual = actual.getPapa();
        }
        return elem;
    }
    private void actualizaFE(NodoBin<T> nodo){
        if(nodo.getDer()==null && nodo.getIzq()==null) {
            return;
        }
        int der = getAltura(nodo.getDer());
        int izq = getAltura(nodo.getIzq());
        nodo.setFe(der-izq);
    }
    public ArrayList<Integer> preOrderFe() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        preOrderFe(root.getDer(),lista);
        return lista;
    }
    private void preOrderFe(NodoBin<T> actual, ArrayList<Integer> lista) {
        if(actual==null) {
            return;
        }
        lista.add(actual.getFe());
        preOrderFe(actual.getIzq(),lista);
        preOrderFe(actual.getDer(),lista);
    }
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<Integer>();
        arbol.inserta(8);
        arbol.inserta(7);
        arbol.inserta(6);
        System.out.println(arbol.preOrder().toString());
        System.out.println(arbol.preOrderFe().toString());
    }
}