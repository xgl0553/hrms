<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="common/taglibs.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>员工管理系统</title>
    <%@ include file="common/meta.jsp" %>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">员工管理控制台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${ctx}/static/avatar.png" class="layui-nav-img">
                    ${user.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javaScript:void(0)" id="editpawordBtn" data-method="editPaword">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="menu">
                <li class="layui-nav-item"><a href="javascript:;" lay-id="menu_employee" data-url="/employee">员工管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" lay-id="menu_worktime" data-url="/worktime">考勤管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" lay-id="menu_salary" data-url="/salary">工资管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" lay-id="menu_dept" data-url="/dept">部门管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" lay-id="menu_user" data-url="/user">账号管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="overflow-y: hidden">
        <!-- 内容主体区域 -->
        <div class="layui-tab" lay-filter="navarea" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this">工作台首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">欢迎来到员工管理系统</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        @2021  员工管理系统
    </div>
</div>
<div hidden id="editPawordIndex">
    <form class="layui-form" action="" lay-filter="editPawordFormIndex" id="editPawordFormIndex">
        <div class="layui-form-item"  lay-filter="paword">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-block">
                <input type="text" name="oldpaword" required  lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item"  lay-filter="paword">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="text" name="paword" required  lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" lay-filter="conformPaword">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-input-block">
                <input type="password"  lay-verify="required|confirmPass" autocomplete="off" placeholder="确认密码"  class="layui-input">
            </div>
        </div>
    </form>
</div>
<script>
    //JavaScript代码区域
    layui.use(['dropdown', 'util','element','form'], function(){
        var dropdown = layui.dropdown
            ,util = layui.util
            ,$ = layui.jquery,form=layui.form,
            element=layui.element;

        form.verify({
            confirmPass:function(value){
                if($('input[name=paword]').val() !== value){
                    return false;
                }
            },
            required:function(value){
                console.log(value);
               if(value == null){
                   return false;
               }
            }
        });
        var tabArray;
        //菜单点击事件
        element.on('nav(menu)', function(data){
            //这里是a链接的自定义属性获取，如果属性不含-符号，可以直接使用.获取，如a.id，如果含-符合，可以使用getAttribute获取
            var a_id = data[0].getAttribute("lay-id");
            var a_url = data[0].getAttribute("data-url");
            var a_title = data[0].innerHTML;

            //否则判断该tab项是否以及存在
            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
            $.each($(".layui-tab-title li[lay-id]"), function () {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") == a_id) {
                    isData = true;
                }
            })
            if (isData == false) {
                //标志为false 新增一个tab项
                //调用layui预置的模块element，新增tab
                element.tabAdd('navarea',{
                    title: a_title
                    ,content:'<iframe src="'+a_url+'" style="width: 99%;" scrolling="auto" frameborder="0"></iframe>'
                        ,id: a_id
                })
                FrameWH();
            }

            //将当前选中tab切换到新增的tab上
            element.tabChange('navarea', a_id);

        });
        function FrameWH() {
            var h = $(window).height()-150;
            $("iframe").css("height",h+"px");
        }
        //触发事件
        var active = {
            editPaword: function () {
                var othis = $(this), dataId = othis.data('id');
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1
                    , title: '修改密码'
                    , content: $('#editPawordIndex')
                    , btn: ['保存', '取消'] //只是为了演示
                    , area: ['600px', '300px'] //宽高
                    , closeBtn: 0
                    , yes: function () {
                        var formData = form.val('editPawordFormIndex');//form lay-filter属性
                        console.log(formData);
                        var layerui = layer;
                        $.ajax({
                            url: "/editPaword",
                            type: "POST",
                            data: JSON.stringify(formData),//传递json类型的参数
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (result) {
                                if (result.code == COMMON_SUCCESS_CODE) {
                                    layerui.msg('操作成功');
                                } else {
                                    layerui.alert(result.msg, {icon: 2});
                                }
                                layerui.closeAll();
                                $("#editPawordFormIndex")[0].reset();
                                form.render();
                            }
                        })

                    }
                    , btn2: function () {
                        layer.closeAll();
                        $("#editPawordFormIndex")[0].reset();
                        form.render();
                    }
                });
            },
        }
        $('#editpawordBtn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });

</script>
</body>
</html>