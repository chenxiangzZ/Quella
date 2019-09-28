<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-xs12">
            <div class="layui-card">
                <div class="layui-card-header">用户列表</div>
                <!--这里写页面的代码-->
                <div class="layui-card-body">
                    <table class="layui-table" id="articaltable" lay-filter="articaltable2" name="str1"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${basePath}/js/artical/index.js"></script>

<script type="text/html" id="indexTpl" name="str2">
    {{d.LAY_TABLE_INDEX+1}}
</script>

<script type="text/html" id="barDemo" name="str3">
<#--    <@shiro.hasPermission name="/artical/manage">-->
<#--        <button class="layui-btn layui-btn-xs" lay-event="edit">进入文章管理</button>-->
<#--    </@shiro.hasPermission>-->
    <button class="layui-btn layui-btn-xs" lay-event="edit">进入文章管理</button>
</script>

