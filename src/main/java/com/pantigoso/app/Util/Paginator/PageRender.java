package com.pantigoso.app.Util.Paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;

    private int totalPaginas;
    private int numElementosPorPagina;
    private int paginaActual;

    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();
        numElementosPorPagina =page.getSize();
        totalPaginas =page.getTotalPages();
        paginaActual = page.getNumber()+ 1 ;//en el controlador dice que empieza desde 0 y aca le sumanos 1 para que empieze desde 1

        int desde,hasta;

        if(totalPaginas <= numElementosPorPagina) {
            desde=1;
            hasta= totalPaginas; //con esto muestra el paginado completo
        }else {
            if(paginaActual <= numElementosPorPagina/2) {
                desde =1;
                hasta= numElementosPorPagina;
            }else if(paginaActual >= totalPaginas - numElementosPorPagina/2) {
                desde=totalPaginas-numElementosPorPagina + 1;
                hasta= numElementosPorPagina;
            }else {
                desde = paginaActual-numElementosPorPagina/2;
                hasta=numElementosPorPagina;
            }
        }
        for(int i=0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde +1));
        }

    }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirts() {
        return page.isFirst();
    }
    public boolean isLast() {
        return page.isLast();
    }
    public boolean isHasNext() {
        return page.hasNext();
    }
    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}