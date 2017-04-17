<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    
    <script type="text/javascript">
       //对用户账号的唯一性进行校验
       var vResult=false;
       
       //1.当用户填完账号后校验一次
       function doVerify(){
         //获取用户填写的账号
         var account=$("#account").val();
         if(account!=""){
           //局部刷新同步提交进行校验
           $.ajax({
             url:"${basePath}nsfw/user_verifyAccount.action",
             data:{"user.account":account},
             async:false,   //非异步提交
             type:"post",
             success:function(msg){
               if("true"!=msg){
                 //用户账号的唯一性校验失败
                 alert("当前用户账号已经存在");
                 //将输入焦点定位到账号输入框中，提示用户再次输入
                 $("#account").focus();
                 vResult=false;
               }else{
                 vResult=true;
               }
             }
           });
         }
       }
       
       //当用户提交表单时进行校验
       function doSubmit(){
         //对用户名进行非空校验
         var name=$("#name");
         if(name.val()==""){
           alert("用户名不能为空");
           name.focus();
           return false;         //阻止表单的提交，同时终止执行下面的代码
         }
         var password=$("#password");
         if(password.val()==""){
           alert("密码不能为空");
           password.focus();
           return false;
         }
         //对用户的账号再次进行校验
         doVerify();
         if(vResult){
           //当校验成功时，提交表单对象
           document.forms[0].submit();
         }
       }
       
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/user_add.action " method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield id="name" name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account" onchange="doVerify()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:password name="user.password" id="password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
              <s:checkboxlist list="#roleList" name="userRoleIds" listKey="roleId" listValue="name"></s:checkboxlist>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td>
               <!-- 当用户填写生日时，自动调用wdatepicker的组件 -->
               <s:textfield id="birthday" name="user.birthday" readonly="true"
                 onfocus="WdatePicker({skin:'whyGreen',el:'birthday',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
               />
            </td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>