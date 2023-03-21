using MediatR;
using Microsoft.EntityFrameworkCore;
using PokerFace.Data;
using PokerFace.Data.Common;
using PokerFace.Data.Hubs;
using PokerFace.Data.Repositories;
using PokerFace.Services;
using PokerFace.Data.Entities;
using PokerFace.Commands.Email;

namespace PokerFace.Web
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            builder.Services.AddMediatR(typeof(Program));
            builder.Services.AddSignalRCore();
            builder.Services.AddSignalR();

            builder.Services.AddControllers();
            builder.Services.AddEndpointsApiExplorer();

            builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseSqlServer(builder.Services.BuildServiceProvider().GetService<IConfiguration>().GetConnectionString("PokerFaceDb")),
                ServiceLifetime.Scoped
                );

            //repos
            builder.Services.AddScoped<IUserRepository, UserRepository>();
            builder.Services.AddScoped<ISessionRepository, SessionRepository>();
            builder.Services.AddScoped<ICardsRepository, CardsRepository>();
            builder.Services.AddScoped<ISettingsRepository, SettingsRepository>();

            //services
            builder.Services.AddScoped<ISignalRService, SignalRService>();
            builder.Services.AddScoped<ISessionService, SessionService>();
        


            //email
            var emailConfig = builder.Configuration
                .GetSection("EmailConfiguration")
                .Get<EmailConfiguration>();
            builder.Services.AddSingleton(emailConfig);
            builder.Services.AddScoped<IEmailSender, EmailSender>();

            //for adding static data 
            //builder.Services.AddSingleton(new StaticData(builder.Services.BuildServiceProvider().GetService<ApplicationDbContext>()));

            builder.Services.AddCors(options =>
            {
                options.AddPolicy(name: "MyCors",
                                  policy =>
                                  {
                                      policy
                                      .WithOrigins("http://localhost:3000")
                                      .AllowCredentials()
                                      .AllowAnyHeader()
                                      .AllowAnyMethod();
                                  });
            });

            var app = builder.Build();

            app.UseCors("MyCors");

            app.UseRouting();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
                endpoints.MapHub<PokerFaceHub>("/api/pokerFaceHub");
            });

            app.UseHttpsRedirection();

            app.UseAuthorization();


            app.Run();
        }
    }
}