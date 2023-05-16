package com.figo.fidobusiness.service;

import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.dto.MaterialDTO;
import com.figo.fidobusiness.dto.MaterialIdDTO;
import com.figo.fidobusiness.dto.ProductDTO;
import com.figo.fidobusiness.dto.ProductIdDTO;

public interface MaterialService extends BaseService<MaterialIdDTO, MaterialDTO> {

    Material getEntity(String materialId);

}
