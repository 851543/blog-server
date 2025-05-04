package com.server.service;

import com.server.domain.dto.AboutDTO;
import com.server.domain.dto.AuroraAdminInfoDTO;
import com.server.domain.dto.AuroraHomeInfoDTO;
import com.server.domain.dto.WebsiteConfigDTO;
import com.server.domain.vo.AboutVO;
import com.server.domain.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
