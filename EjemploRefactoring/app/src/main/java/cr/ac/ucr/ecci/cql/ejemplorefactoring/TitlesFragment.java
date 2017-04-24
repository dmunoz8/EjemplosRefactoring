package cr.ac.ucr.ecci.cql.ejemplorefactoring;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * A fragment representing a list of Items.
 */
public class TitlesFragment extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Agregamos los datos a la lista a partir del arreglo estatico de la clase Buses
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Buses.TITLES));
        // Verificamos si tenemos un frame para agregar los detalles del bus seleccionado
        // Si lo tenemos entonces agregamos el fragmento de detalle en la UI.
        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = (detailsFrame != null) && (detailsFrame.getVisibility() == View.VISIBLE);
        if (savedInstanceState != null) {
            // Restaura la ultima posicion seleccionada en la lista.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        if (mDualPane) {
            // Si mDualPane resaltamos el item seleccionado en la lista.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Mostramos los detalles de la seleccion actual.
            showDetails(mCurCheckPosition);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }
    // Muestra los detalles de una posicion actual de la lista
    void showDetails(int index) {
        mCurCheckPosition = index;
        if (mDualPane) {
            // Puede desplegar los detalles del item seleccionado en la lista.
            getListView().setItemChecked(index, true);
            // Chequea y muestra el fragmento.
            DetailsFragment details = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                // Muestra el detalle de la seleccion.
                details = DetailsFragment.newInstance(index);
                // Inicia una transaccion para inicializar el fragmento en el panel correspondiente
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else {
            // Si mDualPane no permite desplegar el detalle del item seleccionado debemos
            // implementar una actividad para mostrar el detalle del item
 /*
 * Intent intent = new Intent();
 * intent.setClass(getActivity(), DetailsActivity.class);
 * intent.putExtra("index", index);
 * startActivity(intent);
 */
        }
    }
}