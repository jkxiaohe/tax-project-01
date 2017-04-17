<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>我要投诉</title>
    <!-- 引入富文本标记语言 -->
    <script type="text/javascript" src="${basePath }js/ueditor.all.min.js"></script>
    <script type="text/javascript" src="${basePath }js/ueditor.config.js"></script>
    <script type="text/javascript" src="${basePath }js/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
      //设置ueditor的目录
      window.UEDITOR_HOME_URL="${basePath}js/utf8-jsp/";
      var ue=UE.getEditor('editor');
      
      //二级联动
      //根据用户选择的部门查询该部门下的所有员工
      function doSelectDept(){
        //1.获取用户选择的部门值
        var dept=$("#toCompDept option:selected").val();
        if(dept!=""){
          //2.根据部门查询
          $.ajax({
            url:"${basePath}/sys/home_getUserJson.action",
            data:{"dept":dept},
            type:"post",
            dataType : "json",   //返回的数据类型
            success: function(data){
              //将返回的用户列表设置到下拉框中
              if(data!=null && data!="" && data!=undefined){
                if("success"==data.msg){
                  //定位标签
                  var toCompName=$("#toCompName");
                  //清空标签原来的值
                  toCompName.empty();
                  //对data中的用户列表进行迭代
                  $.each(data.userList,function(index,user){
                    toCompName.append("<option value='"+user.name+"'>"+user.name+"</option");
                  });
                  
                  
                  
                }else{
                  alert("获取被投诉人列表失败");
                }
                
                                
              }else{
                alert("获取被投诉人列表失败");
              }
            },
            error:function(){alert("获取被投诉人列表失败");}
          });
        }
      }
      
      //提交表单
      function doSubmit(){
        //1.提交表单并保存
        $.ajax({
          url:"${basePath}/sys/home_complainAdd.action",
          type:"post",
          data:$("#form").serialize(),   //用于将form中的表单参数格式化存储，同时对中文字符进行编码
          async:false,
          success:function(msg){
            if("success"==msg){
              //2.保存成功
              alert("投诉成功");
              //3.刷新父窗口
              window.opener.parent.location.reload(true);
              //4.关闭当前窗口
              window.close();
            }else{
              alert("投诉失败!!!");
            }
          },
          error : function(){alert("投诉失败!!!");}
        });
      }
      
      
    </script>
    
    
</head>
<body>
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉</div></div>
    <div class="tableH2">我要投诉</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">投诉标题：</td>
            <td><s:textfield name="comp.compTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人部门：</td>
            <td>
              <s:select id="toCompDept" name="comp.toCompDept" list="#{'':'请选择','部门A':'部门A','部门B':'部门B'}"
                onchange="doSelectDept()"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人姓名：</td>
            <td>
               <select id="toCompName" name="comp.toCompName"></select>
            </td>
        </tr>
        <tr>
            <td class="tdBg">投诉内容：</td>
            <td><s:textarea id="editor" name="comp.compContent" cssStyle="width:90%;height:160px;" /></td>
        </tr>
        <tr>
            <td class="tdBg">是否匿名投诉：</td>
            <td><s:radio name="comp.isNm" list="#{'0':'非匿名投诉','1':'匿名投诉' }" value="0"/></td>
        </tr>
       
    </table>
    
    <!-- 隐式的传入投诉人的相关信息，并保存到action中的成员变量中 -->
    <!-- 投诉人的信息保存在当前登录用户的域对象中 -->
    <s:hidden name="comp.compCompany" value="%{#session.SYS_USER.dept}"/>
    <s:hidden name="comp.compName" value="%{#session.SYS_USER.name}"/>
    <s:hidden name="comp.compMobile" value="%{#session.SYS_USER.mobile}"/>
    

    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>