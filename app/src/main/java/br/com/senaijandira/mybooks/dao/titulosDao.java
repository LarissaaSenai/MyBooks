package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.Livro;

@Dao
public interface titulosDao {
        @Insert
        long inserir(Livro livro);

        @Update
        void atualizar(Livro livro);

        @Delete
        void deletar(Livro livro);

        @Query("SELECT * FROM livro")
        Livro[] selecionarTodos();
}
