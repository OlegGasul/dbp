package de.mexchange.packagingdb.domain;

import java.util.List;

/**
 * Created by Serozh on 7/2/2016.
 */
public interface AdvancedSearchable extends Searchable{

    public List<String> getSearchableFields();
}
