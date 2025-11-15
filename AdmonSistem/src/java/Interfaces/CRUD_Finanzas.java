package Interfaces;

import java.util.List;
import modelo.Finanza;

public interface CRUD_Finanzas {

    public int agregarFinanza(Finanza f);
    public int actualizarFinanza(Finanza f);
    public int eliminarFinanza(int id);
    public Finanza obtenerFinanzaPorId(int id);
    public List<Finanza> listarFinanzasPorUsuario(int idUsuario);

}
