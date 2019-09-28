<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-xs12">
            <div class="layui-card">
                <div class="layui-card-header">学生信息管理</div>
                <!--这里写页面的代码-->
                <div class="layui-card-body">
                    <div class="layui-card-header">
                        <!-- <span>所有会员列表</span> -->
                        <@shiro.hasPermission name="/student/add">
                            <a id="add" class="layui-btn layui-btn-xs" name="studentadd">
                                <i class="layui-icon"></i>
                                <span>新增</span>
                            </a>
                        </@shiro.hasPermission>
                        <div class="layui-inline" style="float: right">
                            <div class="layui-input-inline">
                                <input name="search" id="searchTest" placeholder="请输学生姓名" lay-verify="required"
                                       autocomplete="off" class="layui-input" type="text">
                            </div>
                            <button class="layui-btn layui-btn-primary" onclick="search()">搜索</button>
                        </div>
                    </div>
                    <table class="layui-table" id="studenttable" lay-filter="studenttable2" name="str3"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/js/page/index.js"></script>
<script type="text/html" id="indexTpl" name="str1">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="barDemo" name="str2">
    <@shiro.hasPermission name="/student/update">
        <button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/student/del">
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
    </@shiro.hasPermission>
</script>

