package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.DepartmentMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DepartmentRepo implements BaseRepo<DepartmentDTO, String> {
    //TODO @gab : remplacer RuntimeException par repoException (apres merge remi)

    @Override
    public @NotNull DepartmentDTO save(@NotNull DepartmentDTO entity) throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Department WHERE name =" + entity.getName());
            if ( rs.next() ){} else {
                stm.executeQuery("INSERT INTO Department VALUES ( " + entity.getName());
            }
            return entity;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail", e);
        }
    }

    @Override
    public @NotNull List<DepartmentDTO> getAll() throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Department");
            return DepartmentMapper.EntityToTDO(rs);
        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull DepartmentDTO getById(@NotNull String name) throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Department WHERE name = " + name);
            return DepartmentMapper.EntityToTDO(rs).get(0);
        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    @Override
    public boolean delete(@NotNull DepartmentDTO entity) throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("DELETE * FROM Department WHERE name = " + entity.getName());
            return true;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail", e);
        }
    }

}