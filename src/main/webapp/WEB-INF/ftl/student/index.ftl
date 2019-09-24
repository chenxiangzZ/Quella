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
                            <a id="add" class="layui-btn layui-btn-xs">
                                <i class="layui-icon"></i>
                                <span>新增</span>
                            </a>
                        </@shiro.hasPermission>
                        <div class="layui-inline" style="float: right">
                            <div class="layui-input-inline">
                                <input name="search" id="searchTest" placeholder="请输学生姓名" lay-verify="required" autocomplete="off" class="layui-input" type="text">
                            </div>
                            <button class="layui-btn layui-btn-primary" onclick="search()">搜索</button>
                        </div>
                    </div>
                    <table class="layui-table" id="studenttable" lay-filter="studenttable2"></table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/html" id="indexTpl">
        {{d.LAY_TABLE_INDEX+1}}
    </script>
    <script type="text/html" id="barDemo">
        <@shiro.hasPermission name="/student/update">
            <button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/student/del">
            <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
        </@shiro.hasPermission>
    </script>
    <script>
        layui.use(['jquery', 'layer', 'table'], function () {
            var layer = layui.layer,table = layui.table;
            //让层自适应iframe
            $('#add').on('click', function(){
                var index = layer.open({
                    type: 2,
                    content: '${basePath}/student/goAdd',
                    area: ['600px', '600px'],
                    maxmin: true,
                    end: function () {
                        table.reload("studenttable",{});
                    }
                });
                parent.layer.iframeAuto(index);
            });
            //表格渲染
            table.render({
                elem: '#studenttable'
                ,url:'${basePath}/student/getStudentPage'
                ,method:'post'
                ,page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'',align:'center', width:70,  title: '序号', toolbar: '#indexTpl'}
                    ,{field:'stuName',  title: '学生姓名'}
                    ,{field:'stuAge',  title: '学生年龄'}
                    ,{field:'stuGrade',  title: '学生成绩'}
                    ,{field:'stuNumber',  title: '学生学号'}
                    ,{field:'stuCreateTime',  title: '创建日期'}
                    ,{field:'right',align:'center', width:150, toolbar: '#barDemo', title: '操作'}
                ]]
            });

            //监听修改按钮
            table.on('tool(studenttable2)', function(obj){
                var data = obj.data;
                if(obj.event === 'edit'){
                    // 编辑
                    var index = layer.open({
                        type: 2,
                        content: '${basePath}/student/goUpdate?id='+data.id,
                        area: ['800px', '500px'],
                        maxmin: true,
                        end: function () {
                            table.reload("studenttable",{});
                        }
                    });
                    parent.layer.iframeAuto(index);

                } else if(obj.event === 'del'){
                    layer.confirm('真的要删除么？', function(index){
                        // 写删除方法
                        $.post("${basePath}/student/del", {"id": data.id}, function (data) {
                            if (data.status == 200) {
                                layer.msg(data.message, {icon: 1, time: 1000});
                                // 前端修改
                                layer.close(index);
                                table.reload("studenttable",{});
                            } else {
                                layer.msg(data.message, {icon: 0, time: 1000});
                                layer.close(index);
                            }
                        });
                    });
                }
            });
        });

        //监听搜索按钮点击事件
        function search(){

            var table = layui.table;
            var search = $('#searchTest').val();
            console.log(search);
            console.log(search);
            table.reload('studenttable', {
                where: { //设定异步数据接口的额外参数，任意设
                    search: search
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        }
    </script>
