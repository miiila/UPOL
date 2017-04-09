from django.contrib import admin

# Register your models here.
from .models import Project, Publication, PublicationType, Section

admin.site.register(Section)
admin.site.register(Project)
admin.site.register(Publication)
admin.site.register(PublicationType)