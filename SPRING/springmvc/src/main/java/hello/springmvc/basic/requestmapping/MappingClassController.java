package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    private Map<Long, String> user = new HashMap<>();
    static Long idx = 0L;

    @GetMapping
    public Map<Long, String> users() {
        return user;
    }

    @PostMapping()
    public Map<Long, String> addUser(){
        user.put(idx, "temp");
        idx += 1;
        return user;
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return user.get(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return user.remove(userId);
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "patch";
    }
}
