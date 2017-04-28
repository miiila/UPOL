from django.shortcuts import render

# Create your views here.

from .models import Section, Publication


def index(request):
    context = {
        'title': 'Jennifer Doe - homepage',
        'menuItems': Section.objects.all(),
        'publications': Publication.objects.all()
    }

    return render(request, 'index.html', context)