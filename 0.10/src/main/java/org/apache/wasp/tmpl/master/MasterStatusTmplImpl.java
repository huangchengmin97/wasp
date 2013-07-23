// Autogenerated Jamon implementation
// /Users/jaywong/data/opensource/hadoop/wasp/trunk/src/main/jamon/./org/apache/wasp/tmpl/master/MasterStatusTmpl.jamon

package org.apache.wasp.tmpl.master;

// 32, 1
import java.util.*;
// 33, 1
import org.apache.hadoop.util.StringUtils;
// 34, 1
import org.apache.hadoop.hbase.util.Bytes;
// 35, 1
import org.apache.hadoop.hbase.util.JvmVersion;
// 36, 1
import org.apache.hadoop.hbase.util.FSUtils;
// 37, 1
import org.apache.wasp.master.FMaster;
// 38, 1
import org.apache.wasp.client.WaspAdmin;
// 39, 1
import org.apache.hadoop.hbase.HConstants;
// 40, 1
import org.apache.wasp.ServerName;
// 41, 1
import org.apache.hadoop.hbase.client.HBaseAdmin;
// 42, 1
import org.apache.wasp.client.FConnectionManager;
// 43, 1
import org.apache.wasp.meta.FTable;
// 44, 1
import org.apache.hadoop.hbase.HBaseConfiguration;

public class MasterStatusTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.wasp.tmpl.master.MasterStatusTmpl.Intf

{
  private final FMaster master;
  private final WaspAdmin admin;
  private final Map<String,Integer> frags;
  private final Set<ServerName> deadServers;
  private final List<ServerName> servers;
  private final boolean showAppendWarning;
  private final boolean catalogJanitorEnabled;
  private final String format;
  private final String filter;
  protected static org.apache.wasp.tmpl.master.MasterStatusTmpl.ImplData __jamon_setOptionalArguments(org.apache.wasp.tmpl.master.MasterStatusTmpl.ImplData p_implData)
  {
    if(! p_implData.getFrags__IsNotDefault())
    {
      p_implData.setFrags(null);
    }
    if(! p_implData.getDeadServers__IsNotDefault())
    {
      p_implData.setDeadServers(null);
    }
    if(! p_implData.getServers__IsNotDefault())
    {
      p_implData.setServers(null);
    }
    if(! p_implData.getShowAppendWarning__IsNotDefault())
    {
      p_implData.setShowAppendWarning(false);
    }
    if(! p_implData.getCatalogJanitorEnabled__IsNotDefault())
    {
      p_implData.setCatalogJanitorEnabled(true);
    }
    if(! p_implData.getFormat__IsNotDefault())
    {
      p_implData.setFormat("html");
    }
    if(! p_implData.getFilter__IsNotDefault())
    {
      p_implData.setFilter("general");
    }
    return p_implData;
  }
  public MasterStatusTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.wasp.tmpl.master.MasterStatusTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    master = p_implData.getMaster();
    admin = p_implData.getAdmin();
    frags = p_implData.getFrags();
    deadServers = p_implData.getDeadServers();
    servers = p_implData.getServers();
    showAppendWarning = p_implData.getShowAppendWarning();
    catalogJanitorEnabled = p_implData.getCatalogJanitorEnabled();
    format = p_implData.getFormat();
    filter = p_implData.getFilter();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 46, 1
    if (format.equals("json") )
    {
      // 46, 30
      jamonWriter.write("\n  ");
      // 47, 3
      {
        org.apache.wasp.tmpl.common.TaskMonitorTmpl __jamon__var_0 = new org.apache.wasp.tmpl.common.TaskMonitorTmpl(this.getTemplateManager());
        __jamon__var_0.setFilter(filter);
        __jamon__var_0.setFormat("json" );
        __jamon__var_0.renderNoFlush(jamonWriter);
      }
      // 47, 68
      jamonWriter.write("\n  ");
      // 48, 3
      return; 
    }
    // 49, 7
    jamonWriter.write("\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<html lang=\"en\">\n  <head>\n    <meta charset=\"utf-8\">\n    <title>Master: ");
    // 54, 20
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getServerName().getHostname()), jamonWriter);
    // 54, 62
    jamonWriter.write("</title>\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <meta name=\"description\" content=\"\">\n    <link href=\"/static/css/bootstrap.css\" rel=\"stylesheet\">\n    <link href=\"/static/css/bootstrap-responsive.css\" rel=\"stylesheet\">\n    <link href=\"/static/css/wasp.css\" rel=\"stylesheet\">\n    <!--[if lt IE 9]>\n      <script src=\"/static/js/html5shiv.js\"></script>\n    <![endif]-->\n  </head>\n\n  <body>\n\n    <div class=\"navbar navbar-fixed-top\">\n      <div class=\"navbar-inner\">\n        <div class=\"container\">\n          <a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">\n            <span class=\"icon-bar\"></span>\n            <span class=\"icon-bar\"></span>\n            <span class=\"icon-bar\"></span>\n          </a>\n          <a class=\"brand\" href=\"/master-status\"><img src=\"/static/wasp_logo_small.jpg\" alt=\"HBase Logo\"/></a>\n          <div class=\"nav-collapse\">\n            <ul class=\"nav\">\n                <li class=\"active\"><a href=\"/\">Home</a></li>\n                <li><a href=\"/tablesDetailed.jsp\">Table Details</a></li>\n                <li><a href=\"/logs/\">Local logs</a></li>\n                <li><a href=\"/logLevel\">Log Level</a></li>\n                <li><a href=\"/dump\">Debug dump</a></li>\n                <li><a href=\"/jmx\">Metrics Dump</a></li>\n                ");
    // 84, 17
    if (HBaseConfiguration.isShowConfInServlet())
    {
      // 84, 64
      jamonWriter.write("\n                <li><a href=\"/conf\">Wasp Configuration</a></li>\n                ");
    }
    // 86, 23
    jamonWriter.write("\n            </ul>\n          </div><!--/.nav-collapse -->\n        </div>\n      </div>\n    </div>\n\n    <div class=\"container\">\n        <div class=\"row inner_header\">\n            <div class=\"page-header\">\n                <h1>Master <small>");
    // 96, 35
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getServerName().getHostname()), jamonWriter);
    // 96, 77
    jamonWriter.write(" </small></h1>\n            </div>\n        </div>\n\n        <div class=\"row\">\n        <!-- Various warnings that cluster admins should be aware of -->\n        ");
    // 102, 9
    if (JvmVersion.isBadJvmVersion() )
    {
      // 102, 45
      jamonWriter.write("\n          <div class=\"alert alert-error\">\n          Your current JVM version ");
      // 104, 36
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(System.getProperty("java.version")), jamonWriter);
      // 104, 76
      jamonWriter.write(" is known to be\n          unstable with Wasp. Please see the\n          <a href=\"http://wiki.apache.org/hadoop/Hbase/Troubleshooting#A18\">Wasp wiki</a>\n          for details.\n          </div>\n        ");
    }
    // 109, 15
    jamonWriter.write("\n        ");
    // 110, 9
    if (showAppendWarning )
    {
      // 110, 34
      jamonWriter.write("\n          <div class=\"alert alert-error\">\n          You are currently running the FMaster without HBase append support enabled.\n          This may result in data loss.\n          Please see the <a href=\"http://wiki.apache.org/hadoop/Hbase/HdfsSyncSupport\">HBase wiki</a>\n          for details.\n          </div>\n        ");
    }
    // 117, 15
    jamonWriter.write("\n        ");
    // 118, 9
    if (master.isInitialized() && !catalogJanitorEnabled )
    {
      // 118, 65
      jamonWriter.write("\n          <div class=\"alert alert-error\">\n          Please note that your cluster is running with the CatalogJanitor disabled. It can be\n          re-enabled from the wasp shell by running the command 'catalogjanitor_switch true'\n          </div>\n        ");
    }
    // 123, 15
    jamonWriter.write("\n\n        <section>\n            <h2>FServers</h2>\n            ");
    // 127, 13
    {
      org.apache.wasp.tmpl.master.FServerListTmpl __jamon__var_1 = new org.apache.wasp.tmpl.master.FServerListTmpl(this.getTemplateManager());
      __jamon__var_1.setServers(servers );
      __jamon__var_1.renderNoFlush(jamonWriter, master);
    }
    // 127, 69
    jamonWriter.write("\n\n            ");
    // 129, 13
    if ((deadServers != null) )
    {
      // 129, 42
      jamonWriter.write("\n                ");
      // 130, 17
      {
        // 130, 17
        __jamon_innerUnit__deadRegionServers(jamonWriter);
      }
      // 130, 40
      jamonWriter.write("\n            ");
    }
    // 131, 19
    jamonWriter.write("\n        </section>\n\n        <section>\n            <h2>Backup Masters</h2>\n            ");
    // 136, 13
    {
      org.apache.wasp.tmpl.master.BackupMasterListTmpl __jamon__var_2 = new org.apache.wasp.tmpl.master.BackupMasterListTmpl(this.getTemplateManager());
      __jamon__var_2.renderNoFlush(jamonWriter, master );
    }
    // 136, 56
    jamonWriter.write("\n        </section>\n\n        <section>\n            <h2>Tables</h2>\n            <div class=\"tabbable\">\n                <ul class=\"nav nav-pills\">\n                    <li class=\"active\">\n                        <a href=\"#tab_userTables\" data-toggle=\"tab\">User Tables</a>\n                    </li>\n                </ul>\n                <div class=\"tab-content\" style=\"padding-bottom: 9px; border-bottom: 1px solid #ddd;\">\n                    <div class=\"tab-pane active\" id=\"tab_userTables\">\n                            ");
    // 149, 29
    {
      // 149, 29
      __jamon_innerUnit__userTables(jamonWriter);
    }
    // 149, 45
    jamonWriter.write("\n                    </div>\n                </div>\n            </div>\n        </section>\n\n      ");
    // 155, 7
    {
      org.apache.wasp.tmpl.master.AssignmentManagerStatusTmpl __jamon__var_3 = new org.apache.wasp.tmpl.master.AssignmentManagerStatusTmpl(this.getTemplateManager());
      __jamon__var_3.renderNoFlush(jamonWriter, master.getAssignmentManager());
    }
    // 155, 88
    jamonWriter.write("\n\n      <!--  <section>\n            ");
    // 158, 13
    {
      org.apache.wasp.tmpl.common.TaskMonitorTmpl __jamon__var_4 = new org.apache.wasp.tmpl.common.TaskMonitorTmpl(this.getTemplateManager());
      __jamon__var_4.setFilter(filter );
      __jamon__var_4.renderNoFlush(jamonWriter);
    }
    // 158, 61
    jamonWriter.write("\n        </section> -->\n\n        <section>\n            <h2>Software Attributes</h2>\n            <table id=\"attributes_table\" class=\"table table-striped\">\n                <tr>\n                    <th>Attribute Name</th>\n                    <th>Value</th>\n                    <th>Description</th>\n                </tr>\n                <tr>\n                    <td>Wasp Version</td>\n                    <td>");
    // 171, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.wasp.util.VersionInfo.getVersion()), jamonWriter);
    // 171, 76
    jamonWriter.write(", r");
    // 171, 79
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.wasp.util.VersionInfo.getRevision()), jamonWriter);
    // 171, 131
    jamonWriter.write("</td><td>Wasp version and revision</td>\n                </tr>\n                <tr>\n                    <td>Wasp Compiled</td>\n                    <td>");
    // 175, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.wasp.util.VersionInfo.getDate()), jamonWriter);
    // 175, 73
    jamonWriter.write(", ");
    // 175, 75
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.wasp.util.VersionInfo.getUser()), jamonWriter);
    // 175, 123
    jamonWriter.write("</td>\n                    <td>When Wasp version was compiled and by whom</td>\n                </tr>\n                <tr>\n                    <td>Wasp Cluster ID</td>\n                    <td>");
    // 180, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getClusterId() != null ? master.getClusterId() : "Not set"), jamonWriter);
    // 180, 96
    jamonWriter.write("</td>\n                    <td>Unique identifier generated for each Wasp cluster</td>\n                </tr>\n                <tr>\n                    <td>HBase Version</td>\n                    <td>");
    // 185, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getVersion()), jamonWriter);
    // 185, 84
    jamonWriter.write(", r");
    // 185, 87
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getRevision()), jamonWriter);
    // 185, 147
    jamonWriter.write("</td><td>HBase version and revision</td>\n                </tr>\n                <tr>\n                    <td>HBase Compiled</td>\n                    <td>");
    // 189, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getDate()), jamonWriter);
    // 189, 81
    jamonWriter.write(", ");
    // 189, 83
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(org.apache.hadoop.hbase.util.VersionInfo.getUser()), jamonWriter);
    // 189, 139
    jamonWriter.write("</td>\n                    <td>When HBase version was compiled and by whom</td>\n                </tr>\n                <tr>\n                    <td>Load average</td>\n                    <td>");
    // 194, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(StringUtils.limitDecimalTo2(master.getFServerManager().getAverageLoad())), jamonWriter);
    // 194, 103
    jamonWriter.write("</td>\n                    <td>Average number of entityGroups per fserver. Naive computation.</td>\n                </tr>\n                ");
    // 197, 17
    if (frags != null )
    {
      // 197, 38
      jamonWriter.write("\n                <tr>\n                    <td>Fragmentation</td>\n                    <td>");
      // 200, 25
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(frags.get("-TOTAL-") != null ? frags.get("-TOTAL-").intValue() + "%" : "n/a"), jamonWriter);
      // 200, 107
      jamonWriter.write("</td>\n                    <td>Overall fragmentation of all tables, including .META. and -ROOT-.</td>\n                </tr>\n                ");
    }
    // 203, 23
    jamonWriter.write("\n                <tr>\n                    <td>Zookeeper Quorum</td>\n                    <td>");
    // 206, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getZooKeeperWatcher().getQuorum()), jamonWriter);
    // 206, 71
    jamonWriter.write("</td>\n                    <td>Addresses of all registered ZK servers. For more, see <a href=\"/zk.jsp\">zk dump</a>.</td>\n                </tr>\n                <tr>\n                    <td>Coprocessors</td>\n                    <td>0</td>\n                    <td>Coprocessors currently loaded by the master</td>\n                </tr>\n                <tr>\n                    <td>FMaster Start Time</td>\n                    <td>");
    // 216, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(new Date(master.getMasterStartTime())), jamonWriter);
    // 216, 68
    jamonWriter.write("</td>\n                    <td>Date stamp of when this FMaster was started</td>\n                </tr>\n                <tr>\n                    <td>FMaster Active Time</td>\n                    <td>");
    // 221, 25
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(new Date(master.getMasterActiveTime())), jamonWriter);
    // 221, 69
    jamonWriter.write("</td>\n                    <td>Date stamp of when this FMaster became active</td>\n                </tr>\n            </table>\n        </section>\n        </div>\n    </div> <!-- /container -->\n\n    <script src=\"/static/js/jquery.min.js\" type=\"text/javascript\"></script>\n    <script src=\"/static/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n    <script src=\"/static/js/tab.js\" type=\"text/javascript\"></script>\n  </body>\n</html>\n\n\n");
  }
  
  
  // 236, 1
  private void __jamon_innerUnit__catalogTables(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 238, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>Table Name</th>\n    ");
    // 241, 5
    if ((frags != null) )
    {
      // 241, 28
      jamonWriter.write("\n        <th title=\"Fragmentation - Will be 0% after a major compaction and fluctuate during normal usage.\">Frag.</th>\n    ");
    }
    // 243, 11
    jamonWriter.write("\n    <th>Description</th>\n</tr>\n\n\n</table>\n");
  }
  
  
  // 251, 1
  private void __jamon_innerUnit__userTables(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 252, 1
    
   FTable[] tables = admin.listTables();
   FConnectionManager.deleteConnection(admin.getConfiguration(), false);

    // 256, 1
    if ((tables != null && tables.length > 0))
    {
      // 256, 45
      jamonWriter.write("\n<table class=\"table table-striped\">\n    <tr>\n        <th>Table Name</th>\n        ");
      // 260, 9
      if ((frags != null) )
      {
        // 260, 32
        jamonWriter.write("\n            <th title=\"Fragmentation - Will be 0% after a major compaction and fluctuate during normal usage.\">Frag.</th>\n        ");
      }
      // 262, 15
      jamonWriter.write("\n        <th>Online EntityGroups</th>\n        <th>Description</th>\n    </tr>\n    ");
      // 266, 5
      for (FTable htDesc : tables)
      {
        // 266, 35
        jamonWriter.write("\n    <tr>\n        <td><a href=table.jsp?name=");
        // 268, 36
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.getTableName()), jamonWriter);
        // 268, 63
        jamonWriter.write(">");
        // 268, 64
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.getTableName()), jamonWriter);
        // 268, 91
        jamonWriter.write("</a> </td>\n        ");
        // 269, 9
        if ((frags != null) )
        {
          // 269, 32
          jamonWriter.write("\n            <td align=\"center\">");
          // 270, 32
          org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(frags.get(htDesc.getTableName()) != null ? frags.get(htDesc.getTableName()).intValue() + "%" : "n/a"), jamonWriter);
          // 270, 138
          jamonWriter.write("</td>\n        ");
        }
        // 271, 15
        jamonWriter.write("\n        <td>");
        // 272, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getAssignmentManager().getEntityGroupStates().getEntityGroupsOfTable(Bytes.toBytes(htDesc.getTableName())).size()), jamonWriter);
        // 272, 139
        jamonWriter.write("\n        <td>");
        // 273, 13
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(htDesc.toString()), jamonWriter);
        // 273, 36
        jamonWriter.write("</td>\n    </tr>\n    ");
      }
      // 275, 12
      jamonWriter.write("\n    <p>");
      // 276, 8
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(tables.length), jamonWriter);
      // 276, 27
      jamonWriter.write(" table(s) in set. [<a href=tablesDetailed.jsp>Details</a>]</p>\n</table>\n");
    }
    // 278, 7
    jamonWriter.write("\n");
  }
  
  
  // 281, 1
  private void __jamon_innerUnit__deadRegionServers(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 283, 1
    if ((deadServers != null && deadServers.size() > 0))
    {
      // 283, 55
      jamonWriter.write("\n<h2>Dead FServers</h2>\n<table class=\"table table-striped\">\n    <tr>\n        <th rowspan=\"");
      // 287, 22
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(deadServers.size() + 1), jamonWriter);
      // 287, 49
      jamonWriter.write("\"></th>\n        <th>ServerName</th>\n    </tr>\n    ");
      // 290, 5
      
       ServerName [] deadServerNames = deadServers.toArray(new ServerName[deadServers.size()]);
         Arrays.sort(deadServerNames);
         for (ServerName deadServerName: deadServerNames) {
           int infoPort = master.getConfiguration().getInt("wasp.fserver.info.port", 50030);
    
      // 296, 5
      jamonWriter.write("<tr>\n        <td>");
      // 297, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(deadServerName), jamonWriter);
      // 297, 33
      jamonWriter.write("</td>\n    </tr>\n    ");
      // 299, 5
      
        }
    
      // 302, 5
      jamonWriter.write("<tr>\n        <th>Total: </th>\n        <td>servers: ");
      // 304, 22
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(deadServers.size()), jamonWriter);
      // 304, 46
      jamonWriter.write("</td>\n    </tr>\n</table>\n");
    }
    // 307, 7
    jamonWriter.write("\n");
  }
  
  
}
