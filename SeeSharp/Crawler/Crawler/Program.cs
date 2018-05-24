using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Crawler
{   //https://www.youtube.com/watch?v=oeuvL1_5UIQ Youtube Tutorial on web crawlers
    class Program
    {   
        
        static void Main(string[] args)
        {
            StartCrawlerAsync();
            Console.ReadLine();
        }

        private static async Task StartCrawlerAsync()
        {   
          
            string url = "https://www.joann.com/projects/projects-videos/arts-and-crafts-projects/?icn=CraftProjects&ici=featuredlink";
            //this GETS the url and stores the document as html. HttpClient is part of HttpClient namespace.
            var httpClient = new HttpClient();
            var html = await httpClient.GetStringAsync(url);
            //using HtmlAgilityPack namespace-- Easier way to parse the html document.
            var htmlDocument = new HtmlDocument();
            htmlDocument.LoadHtml(html);
            var text = htmlDocument.DocumentNode.Descendants("div")
                .Where(node => node.GetAttributeValue("class", "")
                    .Contains("product-tile product-tile--grid")).ToList();    

            foreach (var div in text)
            {   
                //Get ID attribute from each div <used at each new URL>            
                String id = div.GetAttributeValue("data-product_id", "");
                Console.WriteLine(id);
                //Get the links name
                String link = div.GetAttributeValue("data-link_name", "");
                //Joanns has link name + spaces are turned into dashes for the url. Crafts a url that works for joanns.
                link = "https://www.joann.com/" +link.Replace(' ', '-').Replace("?", "%3F")+"/"+id+".html";
                Console.WriteLine(link);
                
               
            }            
        }
    }
}
