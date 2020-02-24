package com.jdh.controller;

import com.jdh.feign.PlayerService;
import com.jdh.pojo.*;
import com.jdh.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
public class PlayerController {

    @Value("${myparameter.uploadPath}")
    public String materialPath;//d:/zp/material/

    @Autowired
    private PlayerService playerService;


    @PostMapping("/bfqStyle/add")
    public JdhResult playerImgAdd(@RequestParam(value = "BfqImgUpInput", required = false) MultipartFile file1, @RequestParam(value = "isPublic", defaultValue = "false") Boolean share) {
        String fileName = null;//文件名字
        Long md5 = null;//md5
        String filePath = null;//保存的图片路劲
        try {
            MyUser currUser = UserContext.getCurrUser();
            if (file1.isEmpty()) return JdhResult.fail("上传失败:null null null");

            fileName = file1.getOriginalFilename();
         //   System.out.println(fileName);
            if (fileName != null && fileName.trim().length() > 0) {
                fileName = FileUtil.renameToUUID(fileName);//获取唯一名称
                String fileType1 = fileName.substring(fileName.lastIndexOf(".") + 1);//文件类型
                fileType1 = fileType1.toLowerCase();
                //验证文件类型.png|.jpg|jpeg|.gif
                if (!fileType1.equals("jpg") && !fileType1.equals("gif")
                        && !fileType1.equals("png") && !fileType1.equals("jpeg") && !fileType1.equals("ico")) {
                    return JdhResult.fail("文件类型错误！！！");
                }
                BufferedImage bufferedImage = ImageIO.read(file1.getInputStream());
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                //  System.out.println("width:"+width+"  heigth:"+height);
                if (width > 33 || height > 33) {
                    return JdhResult.fail("图片尺寸不符合要求！！！");
                }

                String fileMd5 = FileUtil.getFileMd5(file1.getBytes());//获取文件MD5

                //根据MD5查询有没有这张图
                List<PlayerImgDo> playerImgDoByMd5 = playerService.getPlayerImgDoByMd5(fileMd5);
                PlayerStyleDo playerStyleDoByUid = playerService.getPlayerStyleDoByUid(currUser.getId());


                if (playerImgDoByMd5 != null && playerImgDoByMd5.size() > 0) {
                    //有此图
                    //设置图片与用户映射表
                    PlayerImgDo playerImgDo = playerImgDoByMd5.get(0);
                    if (playerStyleDoByUid != null) {
                        playerStyleDoByUid.setPid(playerImgDo.getPid());
                        playerService.changePlayerStyle(playerStyleDoByUid);
                    } else {
                        playerStyleDoByUid = new PlayerStyleDo();
                        playerStyleDoByUid.setPid(playerImgDo.getPid());
                        playerStyleDoByUid.setUid(currUser.getId());
                        playerService.addPlayerStyle(playerStyleDoByUid);
                    }
                    playerImgDo.setUseCount(playerImgDo.getUseCount()+1);
                    playerService.updatePlayerImgDo(playerImgDo);
                    return  JdhResult.success("服务器已存在播放器样式，直接调用无需上传！");
                } else {
                    //无此图
                    //保存图片
                    //D:\zp\material\img\music
                    String dir = FileUtil.getDir(fileName);//目录分离
                    String savePath = materialPath + "img" + File.separator + "music" + dir;
                    FileUtil.uploadFile(file1.getBytes(), savePath, fileName);//保存到磁盘

                    String path = "/material/img/music" + dir.replace("\\", "/") + fileName;//数据库图片地址
                    filePath = savePath + fileName;//文件路劲

                    PlayerImgDo playerImgDo = new PlayerImgDo();
                    playerImgDo.setMd5(fileMd5);
                    playerImgDo.setPlayerUrl(path);//地址
                    playerImgDo.setShare(share);//是否共享
                    playerImgDo.setUid(currUser.getId());//上传者
                    playerImgDo.setUpDate(new Date());//上传时间
                    playerImgDo.setUseCount(1l);//使用次数
                    JdhResult jdhResult = playerService.addPlayerImgDo(playerImgDo);
                    if (jdhResult.getStatus() == 1) {
                        Long pid = Long.valueOf(jdhResult.getData().toString());
                        if (playerStyleDoByUid != null) {
                            playerStyleDoByUid.setPid(pid);
                            playerService.changePlayerStyle(playerStyleDoByUid);
                        } else {
                            playerStyleDoByUid = new PlayerStyleDo();
                            playerStyleDoByUid.setPid(pid);
                            playerStyleDoByUid.setUid(currUser.getId());
                            playerService.addPlayerStyle(playerStyleDoByUid);
                        }
                    } else {
                        throw new RuntimeException("错误");
                    }
                    return  JdhResult.success("上传播放器样式成功！！！");
                }

            }else
                return JdhResult.fail("文件类型错误！！！");

        } catch (Exception e) {
            e.printStackTrace();
            if (filePath != null)
                FileUtil.deleteFile(filePath);
            return JdhResult.fail("未知错误，上传失败！！！");
        }

    }

    /**
     * 根据uid 获取图片信息
     * @return
     */
    @GetMapping("/player/img/myUpload")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoByUid( @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size){
        MyUser currUser = UserContext.getCurrUser();
        return  playerService.getPlayerImgDoByUid(currUser.getId(),page,size);
    }

    /**
     * 根据MD5删除图标
     * @param md5
     * @return
     */
    @PostMapping("/player/img/del/md5/{md5}")
    public JdhResult deletePlayerImgDoByMd5(@PathVariable(name = "md5") String md5){
        try {
            List<PlayerImgDo> imgDoByMd5 = playerService.getPlayerImgDoByMd5(md5);
            if (imgDoByMd5==null||imgDoByMd5.size()<=0){
                return JdhResult.fail("图片不存在，删除失败！");
            }
            MyUser currUser = UserContext.getCurrUser();
            if (!imgDoByMd5.get(0).getUid().equals(currUser.getId())){
                return JdhResult.fail("此图不是你上传的，删除失败！");
            }

            //此方法（getPlayerStyleDoByPid）被修改 只能查出一条记录 目的是防止查到太多 浪费内存！
            List<PlayerStyleDo> playerStyleDoByPid = playerService.getPlayerStyleDoByPid(imgDoByMd5.get(0).getPid());
            if (playerStyleDoByPid==null||playerStyleDoByPid.size()>0){
                return JdhResult.fail("此图标有用户正在使用，暂时无法删除！");
            }

            // /material/img/music/9/2/8816308a9454400dac3d2ab412b47557.gif
            String playerUrl = imgDoByMd5.get(0).getPlayerUrl();
            playerUrl=materialPath+playerUrl.substring(10);
            FileUtil.deleteFile(playerUrl);//删除文件
            return playerService.deletePlayerImgDoByMd5(md5);
        }catch (Exception e){
            e.printStackTrace();
            return  JdhResult.fail("服务器错误，操作失败！");
        }



    }


    @PostMapping("/bfqStyle/change/md5/{md5}")
    public JdhResult playerStyleChangeByMd5(@PathVariable(name = "md5") String md5){
        try {
            if (md5==null)return JdhResult.fail("图标不存在，修改失败！");
            List<PlayerImgDo> playerImgDoByMd5 = playerService.getPlayerImgDoByMd5(md5);
            if (playerImgDoByMd5==null||playerImgDoByMd5.size()<=0)return JdhResult.fail("图标不存在，修改失败！");

            PlayerImgDo playerImgDo = playerImgDoByMd5.get(0);

            MyUser currUser = UserContext.getCurrUser();
            PlayerStyleDo playerStyleDoByUid = playerService.getPlayerStyleDoByUid(currUser.getId());
            if (playerStyleDoByUid != null) {
                playerStyleDoByUid.setPid(playerImgDo.getPid());
                playerService.changePlayerStyle(playerStyleDoByUid);
            } else {
                playerStyleDoByUid = new PlayerStyleDo();
                playerStyleDoByUid.setPid(playerImgDo.getPid());
                playerStyleDoByUid.setUid(currUser.getId());
                playerService.addPlayerStyle(playerStyleDoByUid);
            }
            playerImgDo.setUseCount(playerImgDo.getUseCount()+1);
            playerService.updatePlayerImgDo(playerImgDo);
            return JdhResult.success("修改播放器图标成功！");
        }catch (Exception e){
            e.printStackTrace();
            return  JdhResult.fail("服务器错误，操作失败！");
        }

    }

    /**
     * 获取所有播放器图标
     * map  存在：order asc/desc  ispublis true/false
     * @return
     */
    @GetMapping("/player/share")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoAll(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "size",required = false)Integer size,@RequestParam(value = "field",required = false)String field,@RequestParam(value = "order",required = false) String order){
        //两个值必须同时存在 否则不排序
        if(isEmpty(field)||isEmpty(order)){
            field=null;
            order=null;
        }else {
            field=field.trim();
            order=order.trim();

            if((!field.equals("1")&&!field.equals("2"))||(!order.equals("1")&&!order.equals("2"))){
                field=null;
                order=null;
            }else {
                //1.根据上传时间排序 2.根据热度排序
                if(field.equals("1"))field="up_date";
                else field="use_count";

                //1.asc 2.desc
                if(order.equals("1"))order="asc";
                else order="desc";
            }


        }

        return playerService.getPlayerImgDoAll(page,size,field,order,true);
    }


    /**
     * 查询用户自身背景音乐信息
     * @return
     */

    @GetMapping("/player/music")
    public List<PlayerMusicDo> getMyPlayerMusic(){
        try {
            MyUser currUser = UserContext.getCurrUser();
            return playerService.getPlayerMusicDoByUid(currUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加用户背景音乐信息
     * @return
     */
    @PostMapping("/player/music/add")
    public JdhResult addPlayerMusicDo(@RequestParam(name = "bgMusicUrl",required = false) String bgMusicUrl,@RequestParam(name = "bgMusicName",required = false)String bgMusicName,@RequestParam(name = "bgMusicAuthor",required = false)String bgMusicAuthor){
        try {
            //验证参数格式
            if (bgMusicUrl==null||bgMusicUrl.trim().length()<=0){
                return JdhResult.fail("格式错误，歌曲链接为空值，设置失败！");
            }
            if (bgMusicName==null||bgMusicName.trim().length()<=0){
                return JdhResult.fail("格式错误，歌曲名称存在空值，设置失败！");
            }
            if (bgMusicAuthor==null||bgMusicAuthor.trim().length()<=0){
                return JdhResult.fail("格式错误，歌手名称存在空值，设置失败！");
            }

            if (XssEncode.isIllegalString(bgMusicName)||XssEncode.isIllegalString(bgMusicAuthor)){
                return JdhResult.fail("名称中的内容中请不要使用+,@,&,#,%,\",=,\\,<,>,(,)等特殊字符！");
            }

            if (!isURL(bgMusicUrl)){
                return   JdhResult.fail("歌曲链接格式错误，设置失败，请重新检查后输入！");

            }
            bgMusicUrl=bgMusicUrl.trim();
            bgMusicName=bgMusicName.trim();
            bgMusicAuthor=bgMusicAuthor.trim();

            String fileType1 = bgMusicUrl.substring(bgMusicUrl.lastIndexOf(".") + 1);//文件类型
            fileType1 = fileType1.toLowerCase();
            //验证文件类型.png|.jpg|jpeg|.gif
            if (!fileType1.equals("mp3") && !fileType1.equals("wma")) {
                return JdhResult.fail("请输入合法的链接，只接受mp3和wma。");
            }
            MyUser currUser = UserContext.getCurrUser();

            //查询是否存在信息
            List<PlayerMusicDo> playerMusicDoByUid = playerService.getPlayerMusicDoByUid(currUser.getId());
            if (playerMusicDoByUid==null||playerMusicDoByUid.size()<=0){
                //不存在
                PlayerMusicDo playerMusicDo=new PlayerMusicDo();
                playerMusicDo.setAuthor(bgMusicAuthor);
                playerMusicDo.setMusic(bgMusicUrl);
                playerMusicDo.setMusicName(bgMusicName);
                playerMusicDo.setUid(currUser.getId());
                playerService.addPlayerMusicDo(playerMusicDo);
            }else {
                //存在
                PlayerMusicDo playerMusicDo = playerMusicDoByUid.get(0);
                playerMusicDo.setAuthor(bgMusicAuthor);
                playerMusicDo.setMusic(bgMusicUrl);
                playerMusicDo.setMusicName(bgMusicName);

                playerService.changePlayerMusicDo(playerMusicDo);

            }

            return JdhResult.success("修改背景音乐成功,刷新后生效！");


        }catch (Exception e){
            e.printStackTrace();
            return  JdhResult.fail("服务器错误，操作失败！");
        }
    }


    /**
     * 清除背景音乐
     * @return
     */
    @PostMapping("/player/music/delete")
    public JdhResult deletePlayerMusicDo(){
        try {
            MyUser currUser = UserContext.getCurrUser();
            List<PlayerMusicDo> playerMusicDoByUid = playerService.getPlayerMusicDoByUid(currUser.getId());
            if (playerMusicDoByUid!=null&&playerMusicDoByUid.size()>0){
                //存在
                PlayerMusicDo playerMusicDo = playerMusicDoByUid.get(0);
                if (playerMusicDo.getMusicName()==null)
                    return JdhResult.fail("暂未设置背景音乐，无需清除！");
                playerMusicDo.setUid(currUser.getId());
                playerMusicDo.setMid(playerMusicDo.getMid());
                playerMusicDo.setMusicName("null");
                playerMusicDo.setMusic("null");
                playerMusicDo.setAuthor("null");

                playerService.changePlayerMusicDo(playerMusicDo);
                return JdhResult.success("清除背景音乐成功，当前已无背景音乐！");
            }else {
                //不存在
                return JdhResult.fail("暂未设置背景音乐，无需清除！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("服务器错误，清除失败！");
        }

    }


    /**
     * 是否为空
     * @param str
     * @return 是true 否 false
     */
    public boolean isEmpty(String str){
        if(str==null)return true;
        if(str.trim().length()<=0)return true;
        return false;
    }

    public  boolean isURL(String str){
        //转换为小写
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return  str.matches(regex);
    }
}
