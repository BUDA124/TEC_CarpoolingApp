package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.TermsAndConditionsDTO;
import org.tec.carpooling.common.exceptions.NotFoundException;

public interface TermsService {

    /**
     * Retrieves the current version of the system's terms and conditions.
     *
     * @return TermsAndConditionsDTO containing the current terms.
     * @throws NotFoundException If no current terms and conditions are found/configured.
     */
    TermsAndConditionsDTO getCurrentTermsAndConditions();
}