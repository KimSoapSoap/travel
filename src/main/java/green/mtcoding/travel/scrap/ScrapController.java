package green.mtcoding.travel.scrap;

import green.mtcoding.travel.global.util.Resp;
import green.mtcoding.travel.user.User;
import green.mtcoding.travel.user.UserResponse;
import green.mtcoding.travel.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {

    private final HttpSession session;
    private final ScrapService scrapService;
    private final UserService userService;

    /*           main-start             */
    /*           main-end             */

    /*           theme-start             */
    /*           theme-end             */

    /*           region-start             */
    /*           region-end             */

    /*           hotPlace-start             */
    /*           hotPlace-end             */

    /*           festival-start             */
    /*           festival-end             */

    /*           info-start             */
    /*           info-end             */

    /*           map-start             */
    /*           map-end             */

    /*           user-start             */
    /*           user-end             */

    /*           myPage-start             */

    // 스크랩 페이지
    @GetMapping("/api/my-scrap")
    public String myScrab(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapResponse.MypageScrapDTO model = scrapService.mypageSelectScrap(sessionUser);
        request.setAttribute("model", model);
        return "/mypage/my-scrap";
    }

    // 마이페이지에서 스크랩 Off 하기
    @PostMapping("/api/my-scrapUpdate/{contentId}")
    public @ResponseBody Resp mypageUpdateScrap(@PathVariable String contentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.scrapOnOff(sessionUser, contentId);
        return Resp.ok("성공");
    }

    // 스크랩 온오프
    @PostMapping("/api/scrapOnOff/{contentId}")
    public @ResponseBody Resp scrapOnOff(@PathVariable String contentId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        String result = scrapService.scrapOnOff(user, contentId);
        return Resp.ok(result);
    }


    /*           myPage-end             */

}
