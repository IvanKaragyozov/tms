package pu.master.tmsgui.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import pu.master.tmsgui.data.VadUser;
import pu.master.tmsgui.data.VadUserRepository;


@Service
public class VadUserService
{

    private final VadUserRepository repository;


    public VadUserService(VadUserRepository repository)
    {
        this.repository = repository;
    }


    public Optional<VadUser> get(Long id)
    {
        return repository.findById(id);
    }


    public VadUser update(VadUser entity)
    {
        return repository.save(entity);
    }


    public void delete(Long id)
    {
        repository.deleteById(id);
    }


    public Page<VadUser> list(Pageable pageable)
    {
        return repository.findAll(pageable);
    }


    public Page<VadUser> list(Pageable pageable, Specification<VadUser> filter)
    {
        return repository.findAll(filter, pageable);
    }


    public int count()
    {
        return (int) repository.count();
    }

}
