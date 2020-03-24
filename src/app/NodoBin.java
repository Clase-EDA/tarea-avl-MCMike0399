package app;
public class NodoBin<T extends Comparable<T>> {
    private T elem;
    private NodoBin<T> izq, der, papa;
    private int fe;
    public NodoBin() {
        elem = null;
        izq = null;
        der = null;
        papa = null;
        fe=0;
    }
    public NodoBin(T elem) {
        this.elem = elem;
        izq = null;
        der = null;
        papa = null;
        fe=0;
    }
    public int getFe() {
        return fe;
    }
    public void setFe(int fe) {
        this.fe = fe;
    }
    public NodoBin<T> getIzq() {
        return izq;
    }
    public NodoBin<T> getDer() {
        return der;
    }
    public NodoBin<T> getPapa() {
        return papa;
    }
    public T getElem() {
        return elem;
    }
    public int numDescendiente() {
        int res=0;
        if(izq!=null) {
            res += izq.numDescendiente()+1;
        }
        if(der!=null) {
            res += der.numDescendiente()+1;
        }
        return res;
    }
    public void setIzq(NodoBin<T> izq) {
        this.izq = izq;
    }
    public void setDer(NodoBin<T> der) {
        this.der = der;
    }
    public void setElem(T elem) {
        this.elem = elem;
    }
    public void setPapa(NodoBin<T> papa) {
        this.papa = papa;
    }
    public void cuelga(NodoBin<T> nodo) {
        if(nodo==null) {
            return;
        }
        if(nodo.getElem().compareTo(elem)<0) {
            izq = nodo;
            //System.out.println("izq: " + izq.getElem());
        }
        else {
            der = nodo;     
            //System.out.println("der: " + der.getElem());
        }
        nodo.papa = this;
    }
    public void cuelgaH(NodoBin<T> hijo, String lado){
        if(lado.equals("der"))
            der = hijo;
        else if(lado.equals("izq"))
            izq = hijo;
        else {//si no fue izq o der ejecuta el default (compara el elemento)
            if(hijo.elem.compareTo(hijo.elem)<=0)
                izq = hijo;
            else
                der = hijo;
        }//termina de colgar el hijo
        if(hijo!=null)//verifica que lo que colgó sea diferente de null
            hijo.papa = this;
    }
}