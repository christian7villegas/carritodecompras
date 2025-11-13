package models;
/** * autor: Génesis
 * * fecha: 11/11/2025
 * * descripción: representa el carrito de compras completo, guarda una lista de todos
 * los items agregados*/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleCarro {
    private List<ItemCarro> items;

    public DetalleCarro() {this.items = new ArrayList<>();}

    /**Implementamos un metodo para agregar un producto al carro
     * si el producto ya está en el carrito no lo duplica si no que aumenta la cantidad
     * si no está, lo agrega como nuevo*/
    public void addItemCarro(ItemCarro itemCarro) {
        if(items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if(optionalItemCarro.isPresent()) {
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        }else{
            this.items.add(itemCarro);
        }
    }

    /**
     * Metodo que retorna la lista de productos del carrito
     */
    public List<ItemCarro> getItem() {return items;}


    /**
     * Metodo que calcula el subtotal de todos los productos dentro de mi carrito
     */
    public double getSubtotal() {
        return items.stream().mapToDouble(ItemCarro :: getSubtotal).sum();
    }
    /**
     * Metodo que calcula el Iva total de todos los productos en el carrito
     */
    public double getTotalIva() {
        return getSubtotal() * 0.15;
    }
    /**
     *  Metodo que calcula el total ya con el Iva incluido
     */
    public double getTotal() {
        return getSubtotal() + getTotalIva();
    }


}
