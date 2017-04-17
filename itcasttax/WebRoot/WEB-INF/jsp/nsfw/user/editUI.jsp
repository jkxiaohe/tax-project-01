<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
      var vResult = true;
      //对于编辑页面，账号的唯一性校验必须排除当前用户所使用的账号id
      function doVerify(){
        var account=$("#account");
        if(account.val()!=""){
          var accountValue=account.val();
          //局部刷新同步提交数据
          $.ajax({
            url:"${basePath}nsfw/user_verifyAccount.action",
            type:"post",
            async:false,
            data:{"user.account":accountValue,"user.id":"${user.id}"},
            success : function(msg){
              if("true"!=msg){
                alert("账户名已经存在");
                vResult=false;
                account.focus();
                return false;
              }else{
                vResult=true;
              }
            }
          });
        }
      }
      
      //提交表单时进行校验
      function doSubmit(){
        var name=$("#name");
        if(name.val()==""){
          alert("用户名不能为空");
          name.focus();
          return false;
        }
        
        var password=$("#password");
        if(password.val()==""){
          alert("密码不能为空");
          password.focus();
          return false;
        }
        
        doVerify();
        if(vResult){
          //用户账号已经通过唯一性校验
          document.forms[0].submit();
        }
        
      }
      
      
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }nsfw/user_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div></div>
    <div class="tableH2">编辑用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                
                    <img src="${basePath }upload/<s:property value='user.headImg'/>" width="100" height="100"/>
                    <s:hidden name="user.headImg"/>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name" id="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield name="user.account" id="account" onchange="doVerify()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password" id="password"/></td>
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
              <s:textfield id="birthday" name="user.birthday" readonly="true"
              onfocus="WdatePicker({skin:'whyGreen',el:'birthday',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">
                 <s:param name="value"><s:date name="user.birthday" format="yyyy-MM-dd"/></s:param>
              </s:textfield>
            </td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <!-- 隐式的将用户的主键传递过去 -->
    <s:hidden name="user.id"/>
    <s:hidden name="strName"/>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>